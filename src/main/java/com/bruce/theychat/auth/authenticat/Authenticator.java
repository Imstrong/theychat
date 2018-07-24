package com.bruce.theychat.auth.authenticat;

import com.bruce.theychat.auth.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Authenticator {
    User authenticate(HttpServletRequest request, HttpServletResponse response)throws AuthenticateException;
}
