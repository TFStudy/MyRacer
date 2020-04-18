package com.example.core;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<R extends Repository,V extends IView> {

    protected R mRepository;
    protected WeakReference<V> mView;

    protected abstract void createRepository();

    public BasePresenter(V _v){
        createRepository();
        mView = new WeakReference<>(_v);
    }
}
