package com.bruce.theychat.user.service;

import com.bruce.theychat.user.entity.User;
import org.apache.shiro.authz.Permission;

import javax.management.relation.Role;
import java.util.Set;

public interface UserService {
    User findByUsername(String username);

    Set<Permission> findPermissions(String username);

    Set<Role> findRoles(String username);
}
