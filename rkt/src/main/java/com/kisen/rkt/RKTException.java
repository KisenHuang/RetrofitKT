package com.kisen.rkt;

/**
 * Created by huangwy on 2017/10/25.
 * email: kisenhuang@163.com.
 */

class RKTException extends RuntimeException {

    RKTException(String message) {
        super(message);
    }

    RKTException(String message, Throwable cause) {
        super(message, cause);
    }
}
