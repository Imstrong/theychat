package com.bruce.theychat.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

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
    @Test
    public void testJdbcRealm(){
        Factory<SecurityManager> managerFactory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = managerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
        try{
            subject.login(token);
            System.out.println("登录成功");
        }catch(Exception e){

        }
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }
    @Test
    public void testJDBCRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

    @Test
    public void testGetConnection()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/shiro", "root", "123456");
        System.out.println(conn);
    }
    @Test
    public void testAuthorizing(){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-role-realm.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try{
            subject.login(token);
        }catch(AuthorizationException e){
            e.printStackTrace();
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        Assert.assertEquals(true,subject.hasRole("role1"));

        subject.logout();
    }
}
