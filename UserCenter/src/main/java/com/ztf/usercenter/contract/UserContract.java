package com.ztf.usercenter.contract;

import com.example.core.BasePresenter;
import com.example.core.IModel;
import com.example.core.IView;
import com.example.core.Repository;
import com.example.net.protocol.resp.BaseEntity;
import com.ztf.usercenter.model.protocol.resp.TestUserEntity;
import com.ztf.usercenter.model.protocol.resp.UserEntity;

import io.reactivex.Observable;

public interface UserContract {

    interface UserModel extends IModel{
        Observable<UserEntity> register(UserEntity userEntity);

        Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity);
    }

    abstract class UserRepository extends Repository<UserModel>{
        public abstract Observable<UserEntity> register(UserEntity userEntity);
        public abstract Observable<BaseEntity<TestUserEntity>> register2(TestUserEntity testUserEntity);
    }

    interface UserView<T> extends IView{
        void registerSuccess(T result);
        void registerFailed();
    }

    abstract class UserPresenter extends BasePresenter<UserRepository,UserView>{

        public UserPresenter(UserView _v) {
            super(_v);
        }

        public abstract void register(UserEntity userEntity);
        public abstract void register2(TestUserEntity testUserEntity);
    }

}
