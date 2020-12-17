package com.umeng.soexample.interfaces;

public interface CallBack<T> {

    void fail(String msg);

    void success(T t);
}
