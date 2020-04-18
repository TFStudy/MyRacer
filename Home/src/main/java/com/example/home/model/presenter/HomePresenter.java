package com.example.home.model.presenter;

import android.util.Log;

import com.example.home.callback.ResultCallBack;
import com.example.home.contract.HomeContract;
import com.example.home.model.repository.HoemRepository;

public class HomePresenter extends HomeContract.HomePresenter {
    public HomePresenter(HomeContract.HomeView _v) {
        super(_v);
    }

    @Override
    public void getData() {
        mRepository.getData(new ResultCallBack() {
            @Override
            public void success(String result) {
                if (mView != null && mView.get() != null) {
                    mView.get().success(result);
                } else {
                    Log.d("ztf", "mView or mView.get() is null ");
                }
            }
        });
    }

    @Override
    protected void createRepository() {
        mRepository = new HoemRepository();
    }
}
