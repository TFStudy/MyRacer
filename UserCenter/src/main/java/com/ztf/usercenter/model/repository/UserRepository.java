package com.ztf.usercenter.model.repository;

import com.example.net.protocol.resp.BaseEntity;
import com.ztf.usercenter.contract.UserContract;
import com.ztf.usercenter.model.protocol.resp.TestUserEntity;
import com.ztf.usercenter.model.protocol.resp.UserEntity;
import com.ztf.usercenter.model.service.UserService;

import io.reactivex.Observable;

public class UserRepository extends UserContract.UserRepository {
    @Override
    public Observable<UserEntity> register(UserEntity userEntity) {

        return mModel.register(userEntity);
    }

    @Override
    public Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity) {
        return mModel.register2(testUserEntity);
    }

    @Override
    protected void createModel() {
        mModel=new UserService();
    }
}
