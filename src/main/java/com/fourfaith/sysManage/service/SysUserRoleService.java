//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysUserRole;
import java.util.List;
import java.util.Map;

public interface SysUserRoleService {
    int deleteByPrimaryKey(String var1);

    int insert(SysUserRole var1);

    int insertSelective(SysUserRole var1);

    SysUserRole selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysUserRole var1);

    int updateByPrimaryKey(SysUserRole var1);

    Integer getCount(Map<String, Object> var1);

    List<SysUserRole> getList(Map<String, Object> var1);

    SysUserRole findById(String var1);

    String add(SysUserRole var1);

    String update(SysUserRole var1);

    String deleteByRoleId(String var1);

    List<String> getUserRoleId(String var1);

    String delete(String var1);

    List<String> getRoleIdByUserId(String var1);
}
