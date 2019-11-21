package com.pm.amass.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.common.widget.InputText;
import com.pm.amass.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author pm
 */
public class TestActivity extends AppCompatActivity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_fragment);
        initView();
    }

    private void initView() {
        mInputSingUpItemName = (InputText) findViewById(R.id.input_sing_up_item_name);
        mInputSingUpItemNickname = (InputText) findViewById(R.id.input_sing_up_item_nickname);
        mInputSingUpItemPsd = (InputText) findViewById(R.id.input_sing_up_item_psd);
        mInputSingUpItemPhone = (InputText) findViewById(R.id.input_sing_up_item_phone);
        mInputSingUpItemCode = (InputText) findViewById(R.id.input_sing_up_item_code);
        mBtnStartAuth = (Button) findViewById(R.id.btn_start_auth);
        mBtnStartAuth.setOnClickListener(this);
        mInputSingUpItemInvitation = (InputText) findViewById(R.id.input_sing_up_item_invitation);
        mInputSingUpItemRelation = (TextView) findViewById(R.id.input_sing_up_item_relation);
        mIbtnSelect = (ImageButton) findViewById(R.id.ibtn_select);
        mIbtnSelect.setOnClickListener(this);
        mBtnStartSignUp = (Button) findViewById(R.id.btn_start_sign_up);
        mBtnStartSignUp.setOnClickListener(this);
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
                break;
        }
    }
}
