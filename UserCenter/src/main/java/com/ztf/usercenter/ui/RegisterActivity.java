package com.ztf.usercenter.ui;

import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.core.ui.BaseActivity;
import com.example.net.protocol.resp.BaseEntity;
import com.ztf.imageloader.ImageLoader;
import com.ztf.imageloader.setting.NormalImageSetting;
import com.ztf.usercenter.R;
import com.ztf.usercenter.contract.UserContract;
import com.ztf.usercenter.model.protocol.resp.TestUserEntity;
import com.ztf.usercenter.model.protocol.resp.UserEntity;
import com.ztf.usercenter.presenter.UserPresenter;

public class RegisterActivity extends BaseActivity<UserPresenter> implements UserContract.UserView<BaseEntity<TestUserEntity>> {

    private EditText etRegisterPhonenumber;
    private EditText etRegisterPwd;
    private EditText etRegisterPwd2;
    private Button btnRegisterRegister;
    private ImageView ivRegisterImg;

    @Override
    protected void createPresenter() {
        mPresenter = new UserPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ivRegisterImg = (ImageView) findViewById(R.id.iv_register_img);
        etRegisterPhonenumber = (EditText) findViewById(R.id.et_register_phonenumber);
        etRegisterPwd = (EditText) findViewById(R.id.et_register_pwd);
        etRegisterPwd2 = (EditText) findViewById(R.id.et_register_pwd2);
        btnRegisterRegister = (Button) findViewById(R.id.btn_register_register);
    }

    @Override
    protected void initEvent() {
        btnRegisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = etRegisterPhonenumber.getText().toString();
                String pwd = etRegisterPwd.getText().toString();
                String pwd2 = etRegisterPwd2.getText().toString();

                UserEntity userEntity = new UserEntity();
                userEntity.setPhoneNum(Integer.parseInt(phoneNumber));
                userEntity.setUserPassWord(pwd);
                mPresenter.register(userEntity);

//                TestUserEntity testUserEntity=new TestUserEntity();
//                testUserEntity.setUsername(phoneNumber);
//                testUserEntity.setPwd(pwd);
//                mPresenter.register2(testUserEntity);
            }
        });
    }

    @Override
    protected void initData() {
        ImageLoader.getInstance().loadImage(
                this,
                NormalImageSetting
                        .builder()
                        .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587450999848&di=195c74b948ab68df24bc8ea2ecd73049&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd009b3de9c82d1587e249850820a19d8bd3e42a9.jpg")
//                    .imageRadius(250)
                        .imageView(ivRegisterImg)
                        .build());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void registerSuccess(BaseEntity<TestUserEntity> result) {
        showMsg(result.getData().getUsername());
    }

    @Override
    public void registerFailed() {
        showMsg("register failed");
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }
}
