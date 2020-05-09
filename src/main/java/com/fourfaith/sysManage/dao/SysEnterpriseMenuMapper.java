//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysEnterpriseMenu;
import java.util.List;
import java.util.Map;

public interface SysEnterpriseMenuMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysEnterpriseMenu var1);

    int insertSelective(SysEnterpriseMenu var1);

    SysEnterpriseMenu selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysEnterpriseMenu var1);

    int updateByPrimaryKey(SysEnterpriseMenu var1);

    Integer getCount(Map<String, Object> var1);

    List<SysEnterpriseMenu> getList(Map<String, Object> var1);

    SysEnterpriseMenu findById(String var1);

    int deleteByEnterMenu(String var1);
}
