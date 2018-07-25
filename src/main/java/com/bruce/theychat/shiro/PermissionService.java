package com.bruce.theychat.shiro;

import org.apache.shiro.authz.Permission;

public interface PermissionService {
    Permission createPermission(Permission permission);
    void deletePermission(Permission permission);
}
