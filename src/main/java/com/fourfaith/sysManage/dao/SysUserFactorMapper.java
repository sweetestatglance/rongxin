//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysUserFactor;
import java.util.List;
import java.util.Map;

public interface SysUserFactorMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysUserFactor var1);

    int insertSelective(SysUserFactor var1);

    SysUserFactor selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysUserFactor var1);

    int updateByPrimaryKey(SysUserFactor var1);

    int deleteByUserId(String var1);

    List<SysUserFactor> getList(Map<String, Object> var1);

    int delete(Map<String, Object> var1);
}
