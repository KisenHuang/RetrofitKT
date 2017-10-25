package com.kisen.rkt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huangwy on 2017/10/24.
 * email: kisenhuang@163.com.
 */

public class BaseCallback<T> implements ReturnTypeAdapter<Call<T>> {

    private Callback<T> callback;

    public BaseCallback(Callback<T> callback) {
        this.callback = callback;
    }

    @Override
    public void hook(Call<T> result) {
        result.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (callback != null)
                    callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (callback != null)
                    callback.onFailure(call, t);
            }
        });
    }

}
