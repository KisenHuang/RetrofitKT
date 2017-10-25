package com.kisen.rkt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by huangwy on 2017/10/23.
 * email: kisenhuang@163.com.
 */

@SuppressWarnings("all")
public class RetrofitKTMethod {

    private RetrofitKTService service;
    private Object result;

    RetrofitKTMethod(RetrofitKTService service) {
        this.service = service;
    }

    /**
     * 自定义ReturnTypeAdapter处理请求返回
     *
     * @param adapter 返回处理器
     */
    public RetrofitKTService obtain(ReturnTypeAdapter adapter) {
        Util.checkNull(adapter, "The ReturnTypeAdapter can not be null!");
        adapter.hook(result);
        return service;
    }

    /**
     * 使用全局ReturnTypeAdapter
     */
//    public RetrofitKTService obtain() {
//        Util.checkNull(RetrofitKT.rktConfig, "You need to configure the RKTConfig at RetrofitKT!");
//        Util.checkNull(RetrofitKT.rktConfig.adapter, "You need to configure the ReturnTypeAdapter " +
//                "at RKTConfig of RetrofitKT!");
//        RetrofitKT.rktConfig.adapter.hook(result);
//        return service;
//    }

    /**
     * 获取方法返回值
     *
     * @param clazz  服务类
     * @param method 方法
     * @param args   方法参数
     */
    void invoke(Object clazz, Method method, Object... args) {
        try {
            result = method.invoke(clazz, args);
        } catch (IllegalAccessException e) {
            Util.throwError(e);
        } catch (InvocationTargetException e) {
            throw new RKTException("需要设置Converter.Factory");
        }
    }

}
