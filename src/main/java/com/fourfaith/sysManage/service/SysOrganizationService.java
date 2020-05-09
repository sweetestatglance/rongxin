//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysOrganization;
import java.util.List;
import java.util.Map;

public interface SysOrganizationService {
    int deleteByPrimaryKey(String var1);

    int insert(SysOrganization var1);

    int insertSelective(SysOrganization var1);

    SysOrganization selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysOrganization var1);

    int updateByPrimaryKey(SysOrganization var1);

    Integer getCount(Map<String, Object> var1);

    List<SysOrganization> getList(Map<String, Object> var1);

    SysOrganization findById(String var1);

    List<String> getAllChildIdList(String var1);

    Integer add(SysOrganization var1);

    Integer update(SysOrganization var1);

    Integer delete(String var1);

    SysOrganization getByOrgan(Map<String, Object> var1);
}
