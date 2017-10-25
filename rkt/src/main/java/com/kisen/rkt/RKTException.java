package com.kisen.rkt;

/**
 * Created by huangwy on 2017/10/25.
 * email: kisenhuang@163.com.
 */

public class RKTException extends RuntimeException {

    public RKTException(String message) {
        super(message);
    }

    public RKTException(String message, Throwable cause) {
        super(message, cause);
    }
}
