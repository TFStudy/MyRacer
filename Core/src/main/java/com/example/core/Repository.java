package com.example.core;

/**
 * 数据仓库
 * @param <M>  继承IMmodel
 */
public abstract class Repository<M extends IModel> {

    /**、
     * 数据业务
     */
    protected M mModel;

    /**
     * 创建/初始化业务model
     */
    protected  abstract void createModel();

    public Repository(){
        createModel();
    }

    /**
     * 释放业务model
     */
    protected void releaseModel(){
        if(mModel != null){
            mModel = null;
        }
    }
}
