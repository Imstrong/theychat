package com.bruce.theychat.auth.authenticat;

public class AuthenticateException extends Throwable {
    public AuthenticateException(){

    }
    public AuthenticateException(String message){
        super(message);
    }
    public AuthenticateException(Throwable cause){
        super(cause);
    }
    public AuthenticateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
