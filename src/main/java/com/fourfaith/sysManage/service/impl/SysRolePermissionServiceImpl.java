//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysRolePermissionMapper;
import com.fourfaith.sysManage.model.SysRolePermission;
import com.fourfaith.sysManage.service.SysRolePermissionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    protected Logger logger = Logger.getLogger(SysRolePermissionServiceImpl.class);
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    public SysRolePermissionServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysRolePermissionMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysRolePermission record) {
        int result = this.sysRolePermissionMapper.insert(record);
        return result;
    }

    public int insertSelective(SysRolePermission record) {
        int result = this.sysRolePermissionMapper.insertSelective(record);
        return result;
    }

    public SysRolePermission selectByPrimaryKey(String id) {
        SysRolePermission entity = this.sysRolePermissionMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysRolePermission record) {
        int result = this.sysRolePermissionMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysRolePermission record) {
        int result = this.sysRolePermissionMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysRolePermissionMapper.getCount(params);
        return result;
    }

    public List<SysRolePermission> getList(Map<String, Object> params) {
        return this.sysRolePermissionMapper.getList(params);
    }

    public SysRolePermission findById(String Id) {
        return this.sysRolePermissionMapper.findById(Id);
    }

    public String deleteByRoleMenu(String roleId) {
        String msg = null;
        int result = this.sysRolePermissionMapper.deleteByRoleMenu(roleId);
        if (result > 0) {
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }

        this.logger.info(msg);
        return msg;
    }

    public String add(SysRolePermission model) {
        String msg = null;
        int result = this.sysRolePermissionMapper.insertSelective(model);
        if (result > 0) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }

        this.logger.info(msg);
        return msg;
    }

    public SysRolePermission findByMenuIdAndRoleId(String menuId, String roleId) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        Map<String, Object> params = new HashMap();
        params.put("menuid", menuId);
        params.put("roleid", roleId);
        List<SysRolePermission> list = this.getList(params);
        if (list != null && list.size() > 0) {
            sysRolePermission = (SysRolePermission)list.get(0);
        }

        return sysRolePermission;
    }
}
