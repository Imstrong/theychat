package com.bruce.theychat.auth.authenticat;

import com.bruce.theychat.auth.bean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalCookieAuthenticator implements Authenticator {
    @Override
    public User authenticate(HttpServletRequest request, HttpServletResponse response) throws AuthenticateException {
        String cookie=getCookieFromRequest(request,"cookieName");
        if(cookie==null){
            return null;
        }
        return getUserByCookie(request,cookie);
    }
    private String getCookieFromRequest(HttpServletRequest request,String cookieName){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }
    private User getUserByCookie(HttpServletRequest request,String cookie){
        return null;
    }
}
