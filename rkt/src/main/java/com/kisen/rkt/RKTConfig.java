package com.kisen.rkt;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Created by huangwy on 2017/10/25.
 * email: kisenhuang@163.com.
 */

public class RKTConfig {

    Converter.Factory factory;
    CallAdapter.Factory adapterFactory;
    OkHttpClient client;

    public RKTConfig addConverterFactory(Converter.Factory factory) {
        this.factory = factory;
        return this;
    }

    public RKTConfig addCallAdapterFactory(CallAdapter.Factory adapterFactory) {
        this.adapterFactory = adapterFactory;
        return this;
    }

    public RKTConfig client(OkHttpClient client) {
        this.client = client;
        return this;
    }


}
