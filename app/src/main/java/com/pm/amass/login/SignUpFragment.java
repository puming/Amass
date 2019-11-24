package com.pm.amass.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.common.ux.ToastHelper;
import com.common.widget.AppBar;
import com.common.widget.InputText;
import com.pm.amass.R;
import com.pm.amass.diglog.BottomSelectDialog;
import com.pm.amass.utils.LoginUtil;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author pm
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SignUpFragment";
    /**
     * 请输入姓名
     */
    private InputText mInputSingUpItemName;
    /**
     * 请输入昵称
     */
    private InputText mInputSingUpItemNickname;
    /**
     * 请输入密码
     */
    private InputText mInputSingUpItemPsd;
    /**
     * 请输入手机号
     */
    private InputText mInputSingUpItemPhone;
    /**
     * 请输入验证码
     */
    private InputText mInputSingUpItemCode;
    /**
     * 发送验证
     */
    private Button mBtnStartAuth;
    /**
     * 选填
     */
    private InputText mInputSingUpItemInvitation;
    /**
     * 爸爸
     */
    private TextView mInputSingUpItemRelation;
    private ImageButton mBtnSelect;
    /**
     * 确认注册
     */
    private Button mBtnStartSignUp;

    private LoginViewModel mViewModel;
    private boolean isFetchAuthCode;
    private CompositeDisposable mDisposable = new CompositeDisposable();


    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_up_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppBar appBar = view.findViewById(R.id.appbar_sign_up);
        appBar.getAppbarLeftContainer().setOnClickListener(v -> Objects.requireNonNull(getActivity()).onBackPressed());
        mInputSingUpItemName = (InputText) view.findViewById(R.id.input_sing_up_item_name);
        mInputSingUpItemNickname = (InputText) view.findViewById(R.id.input_sing_up_item_nickname);
        mInputSingUpItemPsd = (InputText) view.findViewById(R.id.input_sing_up_item_psd);
        mInputSingUpItemPhone = (InputText) view.findViewById(R.id.input_sing_up_item_phone);
        mInputSingUpItemCode = (InputText) view.findViewById(R.id.input_sing_up_item_code);
        mBtnStartAuth = (Button) view.findViewById(R.id.btn_start_auth);
        mBtnStartAuth.setOnClickListener(this);
        mInputSingUpItemInvitation = (InputText) view.findViewById(R.id.input_sing_up_item_invitation);
        mInputSingUpItemRelation = (TextView) view.findViewById(R.id.input_sing_up_item_relation);
        mBtnSelect = (ImageButton) view.findViewById(R.id.ibtn_select);
        mBtnSelect.setOnClickListener(this);
        mBtnStartSignUp = (Button) view.findViewById(R.id.btn_start_sign_up);
        mBtnStartSignUp.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_start_auth:
                mBtnStartAuth.requestFocus();
                attemptFetchSmsCode();
                break;
            case R.id.ibtn_select:
                String[] arrays = new String[]{"爸爸", "妈妈", "爷爷","奶奶","外公","外婆"};
                BottomSelectDialog selectDialog = BottomSelectDialog.newInstance(arrays);
                selectDialog.showDialogFragment((AppCompatActivity) getActivity(),
                        selectDialog,
                        String.valueOf(selectDialog.hashCode()));
                selectDialog.setOnOnItemClickListener((pos, content) ->
                        mInputSingUpItemRelation.setText(content));
                break;
            case R.id.btn_start_sign_up:
                attemptSignUp();
                break;
        }
    }

    private void attemptFetchSmsCode() {
        String phone = mInputSingUpItemPhone.getEditableText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastHelper.makeToast(getContext(), "请输入手机号").show();
        } else if (!LoginUtil.isMobileNO(phone)) {
            ToastHelper.makeToast(getContext(), R.string.error_invalid_mobile, Toast.LENGTH_SHORT).show();
        } else {
            startTime(mBtnStartAuth);
            mViewModel.getSmsCode(phone, "reg")
                    .observe(this, resultResource -> {
                        switch (resultResource.status) {
                            case SUCCEED:
                                isFetchAuthCode = true;
                                ToastHelper.makeToast(getContext(), "验证码发送成功").show();
                                break;
                            case ERROR:
                                ToastHelper.makeToast(getContext(), resultResource.message).show();
                                break;
                            default:
                                break;
                        }

                    });
        }

    }

    private void attemptSignUp() {
        Log.d(TAG, "attemptSignUp: ");
        String name = mInputSingUpItemName.getEditableText().toString();
        String nickName = mInputSingUpItemNickname.getEditableText().toString();
        String psd = mInputSingUpItemPsd.getEditableText().toString();
        String phone = mInputSingUpItemPhone.getEditableText().toString();
        String code = mInputSingUpItemCode.getEditableText().toString();
        CharSequence relation = mInputSingUpItemRelation.getText();
        String invitation = mInputSingUpItemInvitation.getEditableText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastHelper.makeToast(getContext(), "请输入名字").show();
        } else if (TextUtils.isEmpty(nickName)) {
            ToastHelper.makeToast(getContext(), "请输入昵称").show();
        } else if (TextUtils.isEmpty(psd)) {
            ToastHelper.makeToast(getContext(), "请输入密码").show();
        } else if (TextUtils.isEmpty(phone)) {
            ToastHelper.makeToast(getContext(), "请输入手机号").show();
        } else if (!LoginUtil.isMobileNO(phone)) {
            ToastHelper.makeToast(getContext(), R.string.error_invalid_mobile, Toast.LENGTH_SHORT).show();
        } else if (!isFetchAuthCode) {
            ToastHelper.makeToast(getContext(), "请输获取验证码").show();
        } else if (TextUtils.isEmpty(code)) {
            ToastHelper.makeToast(getContext(), "请输入验证码").show();
        } else if (!LoginUtil.isAuthCodeNo(code)) {
            ToastHelper.makeToast(getContext(), R.string.error_invalid_sms_code, Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(relation)) {
            ToastHelper.makeToast(getContext(), "请输选择关系").show();
        } else {
            HashMap<String, String> fieldMap = new HashMap<>(12);
            fieldMap.put("token", mViewModel.readTokenFromSp());
            fieldMap.put("phone", phone);
            fieldMap.put("yzm", code);
            fieldMap.put("source", "student");
            if (!TextUtils.isEmpty(invitation)) {
                fieldMap.put("invite_code", invitation);
            }
            fieldMap.put("password", psd);
            fieldMap.put("name", name);
            fieldMap.put("nickname", nickName);
            fieldMap.put("relation", "爸爸");
            mViewModel.getSignUpData(fieldMap)
                    .observe(this, resultResource -> {
                        switch (resultResource.status) {
                            case SUCCEED:
                                getActivity().onBackPressed();
                                break;
                            default:
                                break;
                        }
                    });
        }

    }

}
