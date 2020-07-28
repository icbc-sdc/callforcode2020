package com.icbc.hzyjs.exception;

public class BizException extends Exception {

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
