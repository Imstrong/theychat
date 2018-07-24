package com.bruce.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class AuthenticationTest {
    private static SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();
    @Test
    public void testAuthentication(){
        simpleAccountRealm.addAccount("Mark","123456");

        //构建默认安全管理器
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        //主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("Mark","12345");
        subject.login(token);

        System.out.println("subject.isAuthenticated()"+subject.isAuthenticated());
        subject.isAuthenticated();
    }
}
