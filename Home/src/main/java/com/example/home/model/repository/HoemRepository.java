package com.example.home.model.repository;

import com.example.home.callback.ResultCallBack;
import com.example.home.contract.HomeContract;
import com.example.home.model.service.HomeService;

public class HoemRepository extends HomeContract.HomeRepository {
    @Override
    public void getData(ResultCallBack callBack) {
        mModel.getData(callBack);
    }

    @Override
    protected void createModel() {
        mModel = new HomeService();
    }
}
