package com.bruce.theychat.user.service;

import com.bruce.theychat.user.entity.User;
import org.apache.shiro.authz.Permission;

import javax.management.relation.Role;
import java.util.Set;

public class UserServiceImpl implements UserService {
    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Set<Permission> findPermissions(String username) {
        return null;
    }

    @Override
    public Set<Role> findRoles(String username) {
        return null;
    }
}
