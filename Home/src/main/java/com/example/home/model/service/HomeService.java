package com.example.home.model.service;

import com.example.home.callback.ResultCallBack;
import com.example.home.contract.HomeContract;

public class HomeService implements HomeContract.HomeModel {
    @Override
    public void getData(ResultCallBack callBack) {
        String result = "mvp";
        callBack.success(result);
    }
}
