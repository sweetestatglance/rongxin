//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysRole;
import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysRole var1);

    int insertSelective(SysRole var1);

    SysRole selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysRole var1);

    int updateByPrimaryKey(SysRole var1);

    Integer getCount(Map<String, Object> var1);

    List<SysRole> getList(Map<String, Object> var1);

    SysRole getByRoleId(Map<String, Object> var1);
}
