//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysEnterprise;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface SysEnterpriseService {
    int deleteByPrimaryKey(String var1);

    int insert(SysEnterprise var1);

    int insertSelective(SysEnterprise var1);

    SysEnterprise selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysEnterprise var1);

    int updateByPrimaryKey(SysEnterprise var1);

    Integer getCount(Map<String, Object> var1);

    List<SysEnterprise> getList(Map<String, Object> var1);

    SysEnterprise findById(String var1);

    Integer update(SysEnterprise var1);

    Integer add(SysEnterprise var1);

    SysEnterprise getByEnterPriseCode(String var1);

    void addCreateDefaultPermission(SysEnterprise var1, HttpServletRequest var2);
}
