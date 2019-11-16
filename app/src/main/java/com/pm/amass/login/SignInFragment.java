package com.pm.amass.login;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basics.base.BaseFragment;
import com.basics.repository.Resource;
import com.common.ux.ToastHelper;
import com.pm.amass.BuildConfig;
import com.pm.amass.MainActivity;
import com.pm.amass.R;
import com.pm.amass.bean.Token;

import static com.pm.amass.utils.LoginUtil.isAuthCodeNo;
import static com.pm.amass.utils.LoginUtil.isMobileNO;

/**
 * @author pmcho
 */
public class SignInFragment extends BaseFragment {
    private static final String TAG = "SignInFragment";

    private AppCompatEditText mAppCompatEditTextPhone;
    private AppCompatEditText mAppCompatEditTextCode;
    private SignInViewModel mViewModel;
    private String tokenString;
    private boolean isFeatchCode;
    private RadioGroup mRadioGroup;
    private View mButtonAuthCode;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        subscribeViewModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_in_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        view.findViewById(R.id.btn_sign_up).setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_signIn_to_signUp)
        );
        view.findViewById(R.id.tv_welcome).setOnClickListener(v -> {
            if (BuildConfig.DEBUG) {
                startActivity(new Intent(getContext(), MainActivity.class));
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.finish();
                }
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
                    if (mViewModel.readPhoneFromSp().equals(s.toString())) {
                        isFeatchCode = false;
                    }
                }
            }
        });

        view.findViewById(R.id.btn_sign_in).setOnClickListener(v -> {
            attemptLogin();
        });

        mButtonAuthCode = view.findViewById(R.id.btn_auth_code);
        mButtonAuthCode.setOnClickListener(v -> {
            String phone = mAppCompatEditTextPhone.getText().toString();
            if (!isMobileNO(phone)) {
                ToastHelper.makeToast(getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            //手机号正确缓存
            mViewModel.writePhoneToSp(phone);
            mViewModel.getSmsCode(phone, tokenString)
                    .observe(this, resultResource -> {
                        if (resultResource.status == Resource.Status.SUCCEED) {
                            isFeatchCode = true;
                        }
                    });
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        String cachePhone = mViewModel.readPhoneFromSp();
        if (!TextUtils.isEmpty(cachePhone)) {
            mAppCompatEditTextPhone.setText(cachePhone);
        }
    }


    private void attemptLogin() {
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.rbtn_sign_in_by_phone) {
            // Store values at the time of the login attempt.
            String mobiles = mAppCompatEditTextPhone.getText().toString();
            String code = mAppCompatEditTextCode.getText().toString();

            if (isMobileNO(mobiles) && isAuthCodeNo(code) && isFeatchCode) {
//            showProgress(true);
                mViewModel.getSignInData(mobiles, code)
                        .observe(this, resultLogin -> {
                            if (resultLogin.status == Resource.Status.SUCCEED) {
                                startActivity(new Intent(getContext(), MainActivity.class));
                                FragmentActivity activity = getActivity();
                                if (activity != null) {
                                    activity.finish();
                                }
                            } else if (resultLogin.status == Resource.Status.ERROR) {
                                ToastHelper.makeToast(getContext(), resultLogin.message, Toast.LENGTH_LONG).show();
                            }

//                showProgress(false);
                        });
            } else if (!isMobileNO(mobiles)) {
                if (TextUtils.isEmpty(mobiles)) {
                    ToastHelper.makeToast(getContext(), R.string.error_empth_mobile, Toast.LENGTH_SHORT).show();
                } else {
                    ToastHelper.makeToast(getContext(), R.string.error_invalid_mobile, Toast.LENGTH_SHORT).show();
                }
            } else if (!isFeatchCode) {
                ToastHelper.makeToast(getContext(), getString(R.string.invalid_feath_code)).show();
            } else if (!isAuthCodeNo(code)) {
                if (TextUtils.isEmpty(code)) {
                    ToastHelper.makeToast(getContext(), R.string.error_empth_code, Toast.LENGTH_SHORT).show();
                } else {
                    ToastHelper.makeToast(getContext(), R.string.error_invalid_sms_code, Toast.LENGTH_SHORT).show();
                }
            }
        } else {

        }
    }


    private void subscribeViewModel() {
        String cacheToken = mViewModel.readTokenFromSp();
        tokenString = cacheToken;
        if (TextUtils.isEmpty(cacheToken)) {
            mViewModel.getToken().observe(this, tokenResource -> {
                switch (tokenResource.status) {
                    case SUCCEED:
                        Token token = tokenResource.data;
                        tokenString = token.getData().getToken().getInfo();
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
