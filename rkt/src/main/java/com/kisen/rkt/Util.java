package com.kisen.rkt;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by huangwy on 2017/10/25.
 * email: kisenhuang@163.com.
 */

public class Util {

    public static void checkNull(Object obj, String msg) {
        if (obj == null)
            throw new RKTException(msg);
    }

    public static void checkMethod(Map<String, Method> methodMap, String msg) {
        if (methodMap == null || methodMap.isEmpty())
            throw new RKTException(msg);
    }

    public static void checkMethodName(Map<String, Method> methodMap, String methodName, String msg) {
        if (!methodMap.containsKey(methodName))
            throw new RKTException(msg);
    }

    public static void throwError(Exception e) {
        if (e == null)
            return;
        e.printStackTrace();
        throw new RKTException(e.getMessage(), e.getCause());
    }
}
