//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysUserRoleMapper;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysUserRoleService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    protected Logger logger = Logger.getLogger(SysUserRoleServiceImpl.class);
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysUserRoleMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysUserRole record) {
        int result = this.sysUserRoleMapper.insert(record);
        return result;
    }

    public int insertSelective(SysUserRole record) {
        int result = this.sysUserRoleMapper.insertSelective(record);
        return result;
    }

    public SysUserRole selectByPrimaryKey(String id) {
        SysUserRole entity = this.sysUserRoleMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysUserRole record) {
        int result = this.sysUserRoleMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysUserRole record) {
        int result = this.sysUserRoleMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysUserRoleMapper.getCount(params);
        return result;
    }

    public List<SysUserRole> getList(Map<String, Object> params) {
        return this.sysUserRoleMapper.getList(params);
    }

    public SysUserRole findById(String Id) {
        return this.sysUserRoleMapper.findById(Id);
    }

    public String add(SysUserRole model) {
        String msg = null;
        int result = this.sysUserRoleMapper.insertSelective(model);
        if (result > 0) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }

        this.logger.info(msg);
        return msg;
    }

    public String update(SysUserRole model) {
        String msg = null;

        try {
            int result = this.sysUserRoleMapper.updateByPrimaryKeySelective(model);
            if (result > 0) {
                msg = "更新成功";
            } else {
                msg = "更新失败";
            }

            this.logger.info(msg);
        } catch (Exception var4) {
            var4.printStackTrace();
            msg = "更新失败";
            this.logger.error(msg + var4.getMessage());
        }

        return msg;
    }

    public String deleteByRoleId(String roleId) {
        String msg = null;

        try {
            int result = this.sysUserRoleMapper.deleteByRoleId(roleId);
            if (result > 0) {
                msg = "删除成功";
            } else {
                msg = "删除失败";
            }

            this.logger.info(msg);
        } catch (Exception var4) {
            var4.printStackTrace();
            msg = "删除失败";
            this.logger.error(msg + var4.getMessage());
        }

        return msg;
    }

    public List<String> getUserRoleId(String userId) {
        return this.sysUserRoleMapper.getUserRoleId(userId);
    }

    public String delete(String id) {
        String msg = null;

        try {
            int result = this.sysUserRoleMapper.deleteByPrimaryKey(id);
            if (result > 0) {
                msg = "删除成功";
            } else {
                msg = "删除失败";
            }

            this.logger.info(msg);
        } catch (Exception var4) {
            var4.printStackTrace();
            msg = "删除失败";
            this.logger.error(msg + var4.getMessage());
        }

        return msg;
    }

    public List<String> getRoleIdByUserId(String userId) {
        return this.sysUserRoleMapper.getRoleIdByUserId(userId);
    }
}
