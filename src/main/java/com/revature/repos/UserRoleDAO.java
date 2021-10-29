package com.revature.repos;

import com.revature.models.UserRole;

public interface UserRoleDAO {
    UserRole findById(int id);
    boolean addRole(UserRole userRole);
    boolean updateRole(UserRole userRole);
    boolean deleteRole(UserRole userRole);
}
