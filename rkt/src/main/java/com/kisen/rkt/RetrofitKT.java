package com.kisen.rkt;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by huangwy on 2017/10/23.
 * email: kisenhuang@163.com.
 */

public class RetrofitKT {

    static RKTConfig rktConfig;

    private Retrofit retrofit;

    private RetrofitKT() {
    }

    /**
     * 添加全局配置
     */
    public static void config(RKTConfig config) {
        Util.checkNull(config, "");
        rktConfig = config;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static RetrofitKT build(String url) {
        return new Builder().build(url);
    }

    /**
     * 生成多请求类，并解析
     *
     * @param model 请求模型，model中可以有多个请求方法
     * @return 返回多请求处理类
     */
    public RetrofitKTService service(Class model) {
        Object clazz = retrofit.create(model);
        Util.checkNull(clazz, "Service create failure.");
        Method[] methods = model.getMethods();
        Map<String, Method> methodList = new HashMap<>();
        for (Method m : methods) {
            String methodName = m.getName();
            Class<?>[] paramType = m.getParameterTypes();
            try {
                Method method = clazz.getClass().getMethod(methodName, paramType);
                methodList.put(methodName, method);
            } catch (NoSuchMethodException e) {
                Util.throwError(e);
            }
        }
        return new RetrofitKTService(model, clazz, methodList);
    }

    /**
     * 返回请求模型类对象
     *
     * @param model 请求模型类
     */
    public <T> T create(Class<T> model) {
        return retrofit.create(model);
    }

    private RetrofitKT createRetrofit(String base, Converter.Factory factory,
                                      CallAdapter.Factory adapterFactory,
                                      OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(base);
        if (factory != null || (rktConfig != null && rktConfig.factory != null))
            builder.addConverterFactory(factory != null ? factory : rktConfig.factory);
        if (adapterFactory != null || (rktConfig != null && rktConfig.adapterFactory != null))
            builder.addCallAdapterFactory(adapterFactory != null ? adapterFactory : rktConfig.adapterFactory);
        if (client != null || (rktConfig != null && rktConfig.client != null))
            builder.client(client != null ? client : rktConfig.client);
        retrofit = builder.build();
        return this;
    }

    public static class Builder {
        private Converter.Factory factory;
        private CallAdapter.Factory adapterFactory;
        private OkHttpClient client;

        Builder() {
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            this.factory = factory;
            return this;
        }

        public Builder addCallAdapterFactory(CallAdapter.Factory adapterFactory) {
            this.adapterFactory = adapterFactory;
            return this;
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public RetrofitKT build(String url) {
            return new RetrofitKT().createRetrofit(url,
                    factory, adapterFactory, client);
        }
    }

}
