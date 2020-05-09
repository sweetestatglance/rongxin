//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysRoleArea;
import java.util.List;
import java.util.Map;

public interface SysRoleAreaMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysRoleArea var1);

    int insertSelective(SysRoleArea var1);

    SysRoleArea selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysRoleArea var1);

    int updateByPrimaryKey(SysRoleArea var1);

    Integer getCount(Map<String, Object> var1);

    List<SysRoleArea> getList(Map<String, Object> var1);

    SysRoleArea findById(String var1);

    int deleteAddvcdD(String var1);

    int deleteRoleId(String var1);
}
