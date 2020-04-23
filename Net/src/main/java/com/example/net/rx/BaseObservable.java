package com.example.net.rx;

import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseObservable {
    /**
     * 处理Observable
     * @param tObservable
     * @param observer
     * @param <T>
     */
    public static <T> void doObservable(Observable<T> tObservable, BaseObserver<T> observer, LifecycleOwner lifecycleOwner){
        tObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))
                .subscribe(observer);
    }
}
