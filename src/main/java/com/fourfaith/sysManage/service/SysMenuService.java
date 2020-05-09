//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysMenu;
import java.util.List;
import java.util.Map;

public interface SysMenuService {
    int deleteByPrimaryKey(String var1);

    int insert(SysMenu var1);

    int insertSelective(SysMenu var1);

    Integer update(SysMenu var1);

    SysMenu selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysMenu var1);

    int updateByPrimaryKey(SysMenu var1);

    Integer getCount(Map<String, Object> var1);

    List<SysMenu> getList(Map<String, Object> var1);

    SysMenu findById(String var1);

    SysMenu getByMenuCode(String var1);

    Integer delete(String var1);

    int getMaxMenuOrder();

    Integer add(SysMenu var1);

    List<SysMenu> getListByRoleidAndMenuid(String var1, String var2);

    List<SysMenu> getMenuCodeList(Map<String, Object> var1);

    List<String> getFilterMenus();

    List<SysMenu> getListByRoleId(String var1);

    List<SysMenu> getListByParams(Map<String, Object> var1);
}
