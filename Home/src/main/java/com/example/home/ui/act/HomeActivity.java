package com.example.home.ui.act;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.RouterPath;
import com.example.core.ui.BaseActivity;
import com.example.home.R;
import com.example.home.contract.HomeContract;
import com.example.home.model.presenter.HomePresenter;

@Route(path = RouterPath.HOMEPATH)
public class HomeActivity extends BaseActivity<HomePresenter>implements HomeContract.HomeView {

    private Button hoemActBt;
    private TextView homeActTv;

    @Override
    protected void createPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    protected void initEvent() {
        hoemActBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getData();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        hoemActBt = (Button) findViewById(R.id.hoem_act_bt);
        homeActTv = (TextView) findViewById(R.id.home_act_tv);
    }

    @Override
    public void success(String result) {
        showMsg(result);
        homeActTv.setText(result);
    }

    @Override
    public void failed(Throwable throwable) {
        showMsg(throwable.getMessage());
    }
}
