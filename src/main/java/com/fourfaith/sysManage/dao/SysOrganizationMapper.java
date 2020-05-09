//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysOrganization;
import java.util.List;
import java.util.Map;

public interface SysOrganizationMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysOrganization var1);

    int insertSelective(SysOrganization var1);

    SysOrganization selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysOrganization var1);

    int updateByPrimaryKey(SysOrganization var1);

    Integer getCount(Map<String, Object> var1);

    List<SysOrganization> getList(Map<String, Object> var1);

    SysOrganization getByOrgan(Map<String, Object> var1);
}
