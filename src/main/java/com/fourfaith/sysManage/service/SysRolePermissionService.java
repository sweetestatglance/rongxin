//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysRolePermission;
import java.util.List;
import java.util.Map;

public interface SysRolePermissionService {
    int deleteByPrimaryKey(String var1);

    int insert(SysRolePermission var1);

    int insertSelective(SysRolePermission var1);

    SysRolePermission selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysRolePermission var1);

    int updateByPrimaryKey(SysRolePermission var1);

    Integer getCount(Map<String, Object> var1);

    List<SysRolePermission> getList(Map<String, Object> var1);

    SysRolePermission findById(String var1);

    String deleteByRoleMenu(String var1);

    String add(SysRolePermission var1);

    SysRolePermission findByMenuIdAndRoleId(String var1, String var2);
}
