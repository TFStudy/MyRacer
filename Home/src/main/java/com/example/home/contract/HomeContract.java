package com.example.home.contract;

import com.example.core.BasePresenter;
import com.example.core.IModel;
import com.example.core.IView;
import com.example.core.Repository;
import com.example.home.callback.ResultCallBack;

public interface HomeContract {
    interface HomeModel extends IModel{
        void getData(ResultCallBack callBack);
    }
    abstract class HomeRepository extends Repository<HomeModel>{
       public abstract void getData(ResultCallBack callBack);
    }

    interface HomeView extends IView{
        void success(String msg);
        void failed(Throwable throwable);
    }

    abstract class HomePresenter extends BasePresenter<HomeRepository,HomeView>{
        public HomePresenter(HomeView _v){
            super(_v);
        }
        public abstract void getData();
    }
}
