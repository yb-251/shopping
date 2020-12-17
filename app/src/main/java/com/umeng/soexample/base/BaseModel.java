package com.umeng.soexample.base;


import com.umeng.soexample.interfaces.IModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel implements IModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }
}
