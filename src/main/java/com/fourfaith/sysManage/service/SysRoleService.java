//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.utils.AjaxJson;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface SysRoleService {
    int deleteByPrimaryKey(String var1);

    int insert(SysRole var1);

    int insertSelective(SysRole var1);

    SysRole selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysRole var1);

    int updateByPrimaryKey(SysRole var1);

    Integer getCount(Map<String, Object> var1);

    List<SysRole> getList(Map<String, Object> var1);

    SysRole findById(String var1);

    Integer add(SysRole var1);

    Integer update(SysRole var1);

    SysRole getByRoleId(Map<String, Object> var1);

    AjaxJson deleteRole(String var1, HttpServletRequest var2);

    Integer delete(String var1);
}
