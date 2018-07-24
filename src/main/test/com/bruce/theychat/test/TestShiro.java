package com.bruce.theychat.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class TestShiro {
    @Test
    public void testLogin(){
        //创建一个SecurityManager工厂
        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        获得一个SecurityManager实例
        SecurityManager securityManager = managerFactory.getInstance();
        //将SecurityManager绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //得到Subject，并创建用户名/密码登录token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
        try{
            //执行验证
            subject.login(token);
            System.out.println("登录成功");
        }catch(Exception e){
            System.out.println("登录失败");
        }
        //断言用户已经登录成功
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
        System.out.println("登出成功");
    }
}
