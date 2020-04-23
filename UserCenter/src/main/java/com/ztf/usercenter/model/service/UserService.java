package com.ztf.usercenter.model.service;

import com.example.net.RetrofitFactory;
import com.example.net.protocol.resp.BaseEntity;
import com.ztf.usercenter.contract.UserContract;
import com.ztf.usercenter.model.api.UserApi;
import com.ztf.usercenter.model.protocol.resp.TestUserEntity;
import com.ztf.usercenter.model.protocol.resp.UserEntity;

import io.reactivex.Observable;

public class UserService implements UserContract.UserModel {

    @Override
    public Observable<UserEntity> register(UserEntity userEntity) {
        UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
        Observable<UserEntity> register = userApi.register(userEntity);
        return register;
    }

    @Override
    public Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity) {
        UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
        Observable<BaseEntity<TestUserEntity>> baseEntityObservable = userApi.register2(testUserEntity);

        return baseEntityObservable;
    }
}
