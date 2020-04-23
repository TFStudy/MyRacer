package com.ztf.usercenter.presenter;

import com.example.net.protocol.resp.BaseEntity;
import com.example.net.rx.BaseObservable;
import com.example.net.rx.BaseObserver;
import com.ztf.usercenter.contract.UserContract;
import com.ztf.usercenter.model.protocol.resp.TestUserEntity;
import com.ztf.usercenter.model.protocol.resp.UserEntity;
import com.ztf.usercenter.model.repository.UserRepository;

import io.reactivex.Observable;

public class UserPresenter extends UserContract.UserPresenter {
    public UserPresenter(UserContract.UserView _v) {
        super(_v);
    }

    @Override
    public void register(UserEntity userEntity) {
        Observable<UserEntity> register = mRepository.register(userEntity);
        BaseObservable.doObservable(register,new BaseObserver<UserEntity>(){
            @Override
            public void onNext(UserEntity userEntity) {
                super.onNext(userEntity);
                if (mView!=null&&mView.get()!=null){
                    mView.get().registerSuccess(userEntity);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mView!=null&&mView.get()!=null){
                    mView.get().registerFailed();
                }
            }
        },mView.get().getLifecycleOwner());
    }

    @Override
    public void register2(TestUserEntity testUserEntity) {
        Observable<BaseEntity<TestUserEntity>> baseEntityObservable = mRepository.register2(testUserEntity);
        BaseObservable.doObservable(baseEntityObservable,new BaseObserver<BaseEntity<TestUserEntity>>(){
            @Override
            public void onNext(BaseEntity<TestUserEntity> testUserEntityBaseEntity) {
                super.onNext(testUserEntityBaseEntity);
                if (mView!=null&&mView.get()!=null){
                    mView.get().registerSuccess(testUserEntityBaseEntity);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mView!=null&&mView.get()!=null){
                    mView.get().registerFailed();
                }
            }
        },mView.get().getLifecycleOwner());
    }

    @Override
    protected void createRepository() {
        mRepository=new UserRepository();
    }
}