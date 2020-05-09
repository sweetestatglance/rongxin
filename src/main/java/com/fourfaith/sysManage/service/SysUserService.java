//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.utils.AjaxJson;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface SysUserService {
    int deleteByPrimaryKey(String var1);

    int insert(SysUser var1);

    int insertSelective(SysUser var1);

    SysUser selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysUser var1);

    int updateByPrimaryKey(SysUser var1);

    Integer getCount(Map<String, Object> var1);

    List<SysUser> getList(Map<String, Object> var1);

    SysUser findById(String var1);

    SysUser findByUserName(String var1);

    Integer add(SysUser var1);

    Integer update(SysUser var1);

    AjaxJson deleteUser(String var1, HttpServletRequest var2);

    Integer delete(String var1);

    Integer moveUser(SysUser var1);
}
