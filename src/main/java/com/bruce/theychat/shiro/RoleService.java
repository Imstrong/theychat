package com.bruce.theychat.shiro;

import javax.management.relation.Role;

public interface RoleService {
    public Role createRole(Role role);
    void deleteRole(Role role);
    void correlationPermissions(Long roleId,Long... permissionIds);
    void uncorrelationPermissions(Long roleId,Long... permissionIds);
}
