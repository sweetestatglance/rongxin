//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysMenuMapper;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysRolePermission;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysRolePermissionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    protected Logger logger = Logger.getLogger(SysMenuServiceImpl.class);
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    public SysMenuServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysMenuMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysMenu record) {
        int result = this.sysMenuMapper.insert(record);
        return result;
    }

    public int insertSelective(SysMenu record) {
        int result = this.sysMenuMapper.insertSelective(record);
        return result;
    }

    public SysMenu selectByPrimaryKey(String id) {
        SysMenu entity = this.sysMenuMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysMenu record) {
        int result = this.sysMenuMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysMenu record) {
        int result = this.sysMenuMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysMenuMapper.getCount(params);
        return result;
    }

    public List<SysMenu> getList(Map<String, Object> params) {
        return this.sysMenuMapper.getList(params);
    }

    public SysMenu findById(String Id) {
        return this.sysMenuMapper.selectByPrimaryKey(Id);
    }

    public SysMenu getByMenuCode(String menuCode) {
        return this.sysMenuMapper.getByMenuCode(menuCode);
    }

    public int getMaxMenuOrder() {
        return this.sysMenuMapper.getMaxMenuOrder();
    }

    public List<SysMenu> getListByRoleidAndMenuid(String menuid, String roleid) {
        List<SysMenu> menuList = new ArrayList();
        Map<String, Object> params = new HashMap();
        params.put("roleid", roleid);
        List<String> menuIdList = new ArrayList();
        List<SysRolePermission> sramList = this.sysRolePermissionService.getList(params);
        if (sramList != null && sramList.size() > 0) {
            for(int i = 0; i < sramList.size(); ++i) {
                menuIdList.add(((SysRolePermission)sramList.get(i)).getMenuid());
            }
        }

        Map<String, Object> menuParams = new HashMap();
        menuParams.put("pid", menuid);
        menuParams.put("enabledstate", 1);
        List<SysMenu> allMenuList = this.getList(menuParams);
        if (allMenuList != null && allMenuList.size() > 0) {
            for(int i = 0; i < allMenuList.size(); ++i) {
                SysMenu menu = (SysMenu)allMenuList.get(i);
                if (menuIdList.contains(menu.getId())) {
                    menuList.add(menu);
                }
            }
        }

        return menuList;
    }

    public List<SysMenu> getMenuCodeList(Map<String, Object> params) {
        return this.sysMenuMapper.getMenuCodeList(params);
    }

    public List<String> getFilterMenus() {
        return new ArrayList(Arrays.asList("menuManage", "enterpriseManage"));
    }

    public List<SysMenu> getListByRoleId(String roleid) {
        return this.sysMenuMapper.getListByRoleId(roleid);
    }

    public Integer delete(String id) {
        return this.sysMenuMapper.deleteByPrimaryKey(id);
    }

    public Integer add(SysMenu model) {
        return this.sysMenuMapper.insertSelective(model);
    }

    public Integer update(SysMenu model) {
        return this.sysMenuMapper.updateByPrimaryKeySelective(model);
    }

    public List<SysMenu> getListByParams(Map<String, Object> params) {
        return this.sysMenuMapper.getListByParams(params);
    }
}
