package com.issunjh.ClassManagement.utils;

/**
 * @Description 登录失败后抛出的异常
 * @Author Sunjh
 * @Date 2021/6/7 18:42
 */
public class LoginFailException extends RuntimeException{
    private static final  long serialVersionUID=1L;

    public LoginFailException() {
    }

    public LoginFailException(String message) {
        super(message);
    }

    public LoginFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailException(Throwable cause) {
        super(cause);
    }

    public LoginFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
