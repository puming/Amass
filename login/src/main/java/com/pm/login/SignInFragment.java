package com.pm.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.alibaba.android.arouter.launcher.ARouter;
import com.basics.base.BaseFragment;
import com.basics.repository.Resource;
import com.common.ux.ToastHelper;
import com.pm.login.bean.Token;
import com.pm.login.bean.UserResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.pm.login.utils.LoginUtil.isAuthCodeNo;
import static com.pm.login.utils.LoginUtil.isMobileNO;

/**
 * @author pmcho
 */
public class SignInFragment extends BaseFragment {
    private static final String TAG = "SignInFragment";

    private AppCompatEditText mAppCompatEditTextPhone;
    private AppCompatEditText mAppCompatEditTextCode;
    private AppCompatEditText mAppCompatEditTextAccount;
    private AppCompatEditText mAppCompatEditTextPsd;
    private LoginViewModel mViewModel;
    private String tokenString;
    private boolean isFetchCode;
    private RadioGroup mRadioGroup;
    private Button mButtonAuthCode;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        subscribeViewModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.login_sign_in_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_sign_up).setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_signIn_to_signUp)
        );
        view.findViewById(R.id.tv_welcome).setOnClickListener(v -> {
            if (BuildConfig.DEBUG) {
                startMainActivity();
            }
        });

        mRadioGroup = view.findViewById(R.id.rg);
        ViewGroup mViewGroupSignInPhone = view.findViewById(R.id.sign_in_by_phone_form);
        ViewGroup mViewGroupSignInAccount = view.findViewById(R.id.sign_in_by_account_form);
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbtn_sign_in_by_phone) {
                mViewGroupSignInPhone.setVisibility(View.VISIBLE);
                mViewGroupSignInAccount.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbtn_sign_up_by_account) {
                mViewGroupSignInAccount.setVisibility(View.VISIBLE);
                mViewGroupSignInPhone.setVisibility(View.GONE);
            }
        });
        mAppCompatEditTextPhone = view.findViewById(R.id.input_sign_in_phone);
        mAppCompatEditTextCode = view.findViewById(R.id.input_sign_in_auth_code);
        mAppCompatEditTextAccount = view.findViewById(R.id.input_sign_in_account);
        mAppCompatEditTextPsd = view.findViewById(R.id.input_sign_in_psd);
        mAppCompatEditTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 11 && isMobileNO(s.toString())) {
                    //如果这一次输入的手机号与上一次输入的手机号不一致
                    if (mViewModel.readCachePhoneFromSp().equals(s.toString())) {
                        isFetchCode = false;
                    }
                }
            }
        });

        view.findViewById(R.id.btn_sign_in).setOnClickListener(v -> {
            attemptLogin();
        });

        mButtonAuthCode = view.findViewById(R.id.btn_auth_code);
        mButtonAuthCode.setOnClickListener(v -> attemptFetchCode());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String cachePhone = mViewModel.readCachePhoneFromSp();
        String account = mViewModel.readAccountFromSp();
        if (!TextUtils.isEmpty(cachePhone)) {
            mAppCompatEditTextPhone.setText(cachePhone);
        }
        if (!TextUtils.isEmpty(account)) {
            mAppCompatEditTextAccount.setText(account);
        }
    }

    private void attemptFetchCode() {
        String phone = mAppCompatEditTextPhone.getText().toString();
        if (!isMobileNO(phone)) {
            ToastHelper.makeToast(getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        startTime(mButtonAuthCode);
        //手机号正确缓存
        mViewModel.writeCachePhoneToSp(phone);
        mViewModel.getSmsCode(phone, "login")
                .observe(this, resultResource -> {
                    if (resultResource.status == Resource.Status.SUCCEED) {
                        isFetchCode = true;
                    }
                });
    }

    private void startTime(final TextView tvGetCode) {
        final long codeTimes = 60L;
        Disposable disposable1 = Observable.interval(0L, 1L, TimeUnit.SECONDS)
                .take(codeTimes + 1L)
                .map(aLong -> codeTimes - aLong)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    mDisposable.add(disposable);
                    tvGetCode.setEnabled(false);
                })
                .subscribe(
                        (value) -> {
                            tvGetCode.setText(String.valueOf(value) + "s");
                        },
                        (error) -> {
                        },
                        () -> {
                            tvGetCode.setEnabled(true);
                            tvGetCode.setText("重新获取");
                        });
        mDisposable.add(disposable1);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


    private void attemptLogin() {
        HashMap<String, String> fieldMap = new HashMap<>(12);
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.rbtn_sign_in_by_phone) {
            String mobiles = mAppCompatEditTextPhone.getText().toString();
            String code = mAppCompatEditTextCode.getText().toString();
            // Store values at the time of the login attempt.
            if (isMobileNO(mobiles) && isAuthCodeNo(code) && isFetchCode) {
//            showProgress(true);
                fieldMap.put("phone", mobiles);
                fieldMap.put("yzm", code);
                fieldMap.put("way", "phone");
                realLogin(fieldMap);
//                showProgress(false);
            } else if (!isMobileNO(mobiles)) {
                if (TextUtils.isEmpty(mobiles)) {
                    ToastHelper.makeToast(getContext(), R.string.login_error_empty_mobile, Toast.LENGTH_SHORT).show();
                } else {
                    ToastHelper.makeToast(getContext(), R.string.login_error_invalid_mobile, Toast.LENGTH_SHORT).show();
                }
            } else if (!isFetchCode) {
                ToastHelper.makeToast(getContext(), getString(R.string.login_invalid_fetch_code)).show();
            } else if (!isAuthCodeNo(code)) {
                if (TextUtils.isEmpty(code)) {
                    ToastHelper.makeToast(getContext(), R.string.login_error_empty_code, Toast.LENGTH_SHORT).show();
                } else {
                    ToastHelper.makeToast(getContext(), R.string.login_error_invalid_sms_code, Toast.LENGTH_SHORT).show();
                }
            }
        } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.rbtn_sign_up_by_account) {

            String account = mAppCompatEditTextAccount.getText().toString();
            String psd = mAppCompatEditTextPsd.getEditableText().toString();
            if (!isMobileNO(account)) {
                if (TextUtils.isEmpty(account)) {
                    ToastHelper.makeToast(getContext(), R.string.login_error_empty_account, Toast.LENGTH_SHORT).show();
                } else {
                    ToastHelper.makeToast(getContext(), R.string.login_error_invalid_account, Toast.LENGTH_SHORT).show();
                }
            } else if (TextUtils.isEmpty(psd)) {
                ToastHelper.makeToast(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();
            } else {
                fieldMap.put("number", account);
                fieldMap.put("password", psd);
                fieldMap.put("way", "number");
                realLogin(fieldMap);
            }
        }
    }

    private void realLogin(Map fieldMap) {
        mViewModel.getSignInData(fieldMap)
                .observe(this, resultLogin -> {
                    if (resultLogin.status == Resource.Status.SUCCEED) {
                        UserResult.User user = resultLogin.data.getData();
                        if (user == null) {
                            ToastHelper.makeToast(getContext(), "登录失败", Toast.LENGTH_LONG).show();
                        } else {
                            mViewModel.writeAccountToSp(user.getPhone());
                            mViewModel.writeUidToSp(String.valueOf(user.getId()));
                            startMainActivity();
                        }
                    } else if (resultLogin.status == Resource.Status.ERROR) {
                        ToastHelper.makeToast(getContext(), resultLogin.message, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void startMainActivity() {
//        startActivity(new Intent(getContext(), MainActivity.class));
        /*Intent intent = new Intent("com.pm.amass.intent.action.MAIN");
        intent.setClassName("com.pm.amass","com.pm.amass.MainActivity");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }*/
        ARouter.getInstance().build(RoutePath.MAIN_ACTIVITY).navigation(getActivity());
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }


    private void subscribeViewModel() {
        String cacheToken = mViewModel.readTokenFromSp();
        tokenString = cacheToken;
        if (TextUtils.isEmpty(cacheToken)) {
            mViewModel.getToken().observe(this, tokenResource -> {
                switch (tokenResource.status) {
                    case SUCCEED:
                        Token token = tokenResource.data.getData();
                        tokenString = token.getToken().getInfo();
                        mViewModel.writeTokenToSp(tokenString);
                        Log.d(TAG, "subscribeViewModel: tokenString" + tokenString);
                        break;
                    default:
                        break;
                }
            });
        }

    }

}
