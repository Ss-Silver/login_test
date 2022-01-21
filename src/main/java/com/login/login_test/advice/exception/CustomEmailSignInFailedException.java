package com.login.login_test.advice.exception;

public class CustomEmailSignInFailedException extends RuntimeException{
    public CustomEmailSignInFailedException() {
    }

    public CustomEmailSignInFailedException(String msg) {
        super(msg);
    }

    public CustomEmailSignInFailedException(String msg, Throwable t) {
        super(msg, t);
    }
}
