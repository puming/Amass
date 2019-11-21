package com.pm.amass.login;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.common.widget.AppBar;
import com.common.widget.InputText;
import com.pm.amass.R;

import java.util.Objects;

/**
 * @author pm
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {
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
    private ImageButton mIbtnSelect;
    /**
     * 确认注册
     */
    private Button mBtnStartSignUp;

    private SignUpViewModel mViewModel;

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
        mIbtnSelect = (ImageButton) view.findViewById(R.id.ibtn_select);
        mIbtnSelect.setOnClickListener(this);
        mBtnStartSignUp = (Button) view.findViewById(R.id.btn_start_sign_up);
        mBtnStartSignUp.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_start_auth:
                break;
            case R.id.ibtn_select:
                break;
            case R.id.btn_start_sign_up:
                attemptSign();
                break;
        }
    }

    private void attemptSign() {
    }

}
