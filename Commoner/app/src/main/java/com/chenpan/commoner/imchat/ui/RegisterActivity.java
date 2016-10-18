package com.chenpan.commoner.imchat.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenpan.commoner.imchat.R;
import com.chenpan.commoner.imchat.base.BaseActivity;
import com.chenpan.commoner.imchat.utils.MobileUtils;
import com.chenpan.commoner.imchat.utils.ToastFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.regist_phone_text)
    TextView mRegistPhoneText;
    @Bind(R.id.regist_send_code)
    Button mRegistSendCode;
    @Bind(R.id.regist_phone_no)
    EditText mRegistPhoneNo;
    @Bind(R.id.placeholder_phone)
    TextView mPlaceholderPhone;
    @Bind(R.id.item_phone)
    RelativeLayout mItemPhone;
    @Bind(R.id.regist_checkcode_text)
    TextView mRegistCheckcodeText;
    @Bind(R.id.placeholder_phone_code)
    TextView mPlaceholderPhoneCode;
    @Bind(R.id.regist_checkcode)
    EditText mRegistCheckcode;
    @Bind(R.id.item_code)
    RelativeLayout mItemCode;
    @Bind(R.id.regist_password_text)
    TextView mRegistPasswordText;
    @Bind(R.id.placeholder_phone_pw1)
    TextView mPlaceholderPhonePw1;
    @Bind(R.id.placeholder_phone_pw2)
    TextView mPlaceholderPhonePw2;
    @Bind(R.id.regist_password)
    EditText mRegistPassword;
    @Bind(R.id.item_password)
    RelativeLayout mItemPassword;
    @Bind(R.id.regist_password_text_confirm)
    TextView mRegistPasswordTextConfirm;
    @Bind(R.id.regist_password_confirm)
    EditText mRegistPasswordConfirm;
    @Bind(R.id.item_password2)
    RelativeLayout mItemPassword2;
    @Bind(R.id.regist_submit)
    Button mRegistSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean isSetStatusBar() {
        return true;
    }

    @OnClick({R.id.regist_send_code, R.id.regist_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regist_send_code:
                String phone=mRegistPhoneNo.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)){
                    if (MobileUtils.isMobileNo(phone)){
                        new TimeCount(60 * 1000, 1000).start();
                    }else{
                        ToastFactory.getToast(this,"请输入正确的手机号码").show();
                    }
                }else{
                    ToastFactory.getToast(this,"请输入手机号码").show();
                }

                break;
            case R.id.regist_submit:
                break;
        }
    }
    /***
     * 获取验证码倒计时
     */
    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mRegistSendCode.setClickable(false);
            mRegistSendCode.setText(millisUntilFinished / 1000 + "秒后可重发");
        }

        @Override
        public void onFinish() {
            mRegistSendCode.setText("重新获取");
            mRegistSendCode.setClickable(true);
        }
    }
}
