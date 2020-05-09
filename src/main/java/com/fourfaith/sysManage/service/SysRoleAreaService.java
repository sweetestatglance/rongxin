//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysRoleArea;
import java.util.List;
import java.util.Map;

public interface SysRoleAreaService {
    int deleteByPrimaryKey(String var1);

    int insert(SysRoleArea var1);

    int insertSelective(SysRoleArea var1);

    SysRoleArea selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysRoleArea var1);

    int updateByPrimaryKey(SysRoleArea var1);

    Integer getCount(Map<String, Object> var1);

    List<SysRoleArea> getList(Map<String, Object> var1);

    SysRoleArea findById(String var1);

    String deleteRoleId(String var1);

    String add(SysRoleArea var1);

    String deleteAddvcdD(String var1);

    List<String> getAddvcdDIdList(Map<String, Object> var1);

    List<SysRoleArea> getListByRoleId(String var1);

    SysRoleArea findByAddvcdIdAndRoleId(String var1, String var2);
}
