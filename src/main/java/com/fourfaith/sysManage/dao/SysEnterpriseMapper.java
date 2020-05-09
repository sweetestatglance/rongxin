//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysEnterprise;
import java.util.List;
import java.util.Map;

public interface SysEnterpriseMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysEnterprise var1);

    int insertSelective(SysEnterprise var1);

    SysEnterprise selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysEnterprise var1);

    int updateByPrimaryKey(SysEnterprise var1);

    Integer getCount(Map<String, Object> var1);

    List<SysEnterprise> getList(Map<String, Object> var1);

    SysEnterprise getByEnterPriseCode(String var1);
}
