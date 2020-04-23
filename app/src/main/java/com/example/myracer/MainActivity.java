package com.example.myracer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.router.RouterPath;

public class MainActivity extends AppCompatActivity {

    private TextView tv_test_act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        tv_test_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterPath.HOMEPATH).navigation();
            }
        });
    }

    private void initView() {
        tv_test_act = (TextView) findViewById(R.id.tv_test_act);
    }
}
