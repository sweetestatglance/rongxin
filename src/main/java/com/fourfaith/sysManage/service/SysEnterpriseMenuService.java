//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysEnterpriseMenu;
import java.util.List;
import java.util.Map;

public interface SysEnterpriseMenuService {
    int deleteByPrimaryKey(String var1);

    int insert(SysEnterpriseMenu var1);

    int insertSelective(SysEnterpriseMenu var1);

    SysEnterpriseMenu selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysEnterpriseMenu var1);

    int updateByPrimaryKey(SysEnterpriseMenu var1);

    Integer getCount(Map<String, Object> var1);

    List<SysEnterpriseMenu> getList(Map<String, Object> var1);

    SysEnterpriseMenu findById(String var1);

    void saveEnterMenu(String var1, String var2, String var3);

    String deleteByEnterMenu(String var1);

    String add(SysEnterpriseMenu var1);
}
