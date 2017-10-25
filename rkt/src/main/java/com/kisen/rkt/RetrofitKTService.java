package com.kisen.rkt;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by huangwy on 2017/10/23.
 * email: kisenhuang@163.com.
 */

public class RetrofitKTService {

    private Map<String, Method> methodMap;
    private Object model;
    private Object clazz;

    RetrofitKTService(Class model, Object clazz, Map<String, Method> methods) {
        this.model = model;
        this.clazz = clazz;
        methodMap = methods;
    }

    /**
     * 根据请求名称和参数生成单一请求
     *
     * @param methodName 请求名称
     * @param parameters 请求参数
     * @return 单一请求
     */
    public RetrofitKTMethod method(String methodName, Object... parameters) {
        String serviceName = model.getClass().getName();
        Util.checkMethod(methodMap, serviceName + " has not method!");
        Util.checkMethodName(methodMap, methodName, serviceName +
                " can not find the method " + "'" + methodName + "'");
        Method method = methodMap.get(methodName);
        RetrofitKTMethod rktMethod = new RetrofitKTMethod(this);
        rktMethod.invoke(clazz, method, parameters);
        return rktMethod;
    }

}
