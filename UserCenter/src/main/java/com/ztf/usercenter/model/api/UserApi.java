package com.ztf.usercenter.model.api;

import com.example.net.protocol.resp.BaseEntity;
import com.ztf.usercenter.model.protocol.resp.TestUserEntity;
import com.ztf.usercenter.model.protocol.resp.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/videouser/register")
    Observable<UserEntity> register(@Body UserEntity userEntity);

    @POST("api/User/register")
    Observable<BaseEntity<TestUserEntity>> register2(@Body TestUserEntity testUserEntity);
}