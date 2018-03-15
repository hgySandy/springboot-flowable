package com.home.demo.dao;

import java.util.List;


import com.home.demo.bean.Permission;

public interface PermissionDao {
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(String userId);
    
}
