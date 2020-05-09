//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysUser var1);

    int insertSelective(SysUser var1);

    SysUser selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysUser var1);

    int updateByPrimaryKey(SysUser var1);

    Integer getCount(Map<String, Object> var1);

    List<SysUser> getList(Map<String, Object> var1);

    SysUser findByUserName(String var1);

    int deleteByUserId(String var1);
}
