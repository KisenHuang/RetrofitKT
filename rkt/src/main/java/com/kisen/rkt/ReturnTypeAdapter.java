package com.kisen.rkt;

/**
 * Created by huangwy on 2017/10/24.
 * email: kisenhuang@163.com.
 */

public interface ReturnTypeAdapter<T> {

    void hook(T result);

}
