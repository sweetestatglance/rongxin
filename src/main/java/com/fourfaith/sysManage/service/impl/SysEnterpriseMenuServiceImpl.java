//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysEnterpriseMenuMapper;
import com.fourfaith.sysManage.model.SysEnterpriseMenu;
import com.fourfaith.sysManage.model.SysRolePermission;
import com.fourfaith.sysManage.service.SysEnterpriseMenuService;
import com.fourfaith.sysManage.service.SysRolePermissionService;
import com.fourfaith.utils.CommonUtil;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysEnterpriseMenuService")
public class SysEnterpriseMenuServiceImpl implements SysEnterpriseMenuService {
    protected Logger logger = Logger.getLogger(SysEnterpriseMenuServiceImpl.class);
    @Autowired
    private SysEnterpriseMenuMapper sysEnterpriseMenuMapper;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    public SysEnterpriseMenuServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysEnterpriseMenuMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysEnterpriseMenu record) {
        int result = this.sysEnterpriseMenuMapper.insert(record);
        return result;
    }

    public int insertSelective(SysEnterpriseMenu record) {
        int result = this.sysEnterpriseMenuMapper.insertSelective(record);
        return result;
    }

    public SysEnterpriseMenu selectByPrimaryKey(String id) {
        SysEnterpriseMenu entity = this.sysEnterpriseMenuMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysEnterpriseMenu record) {
        int result = this.sysEnterpriseMenuMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysEnterpriseMenu record) {
        int result = this.sysEnterpriseMenuMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysEnterpriseMenuMapper.getCount(params);
        return result;
    }

    public List<SysEnterpriseMenu> getList(Map<String, Object> params) {
        return this.sysEnterpriseMenuMapper.getList(params);
    }

    public SysEnterpriseMenu findById(String Id) {
        return this.sysEnterpriseMenuMapper.findById(Id);
    }

    public String add(SysEnterpriseMenu model) {
        String msg = null;
        int result = this.sysEnterpriseMenuMapper.insertSelective(model);
        if (result > 0) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }

        this.logger.info(msg);
        return msg;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void saveEnterMenu(String ids, String enterPriseId, String roleId) {
        this.deleteByEnterMenu(enterPriseId);
        if (roleId != null) {
            this.sysRolePermissionService.deleteByRoleMenu(roleId);
        }

        if (StringUtils.isNotEmpty(ids)) {
            String[] idArray = ids.split(",");

            for(int i = 0; i < idArray.length; ++i) {
                SysEnterpriseMenu enterMenu = new SysEnterpriseMenu();
                enterMenu.setId(CommonUtil.getRandomUUID());
                enterMenu.setEnterpriseid(enterPriseId);
                enterMenu.setMenuid(idArray[i]);
                this.add(enterMenu);
                if (roleId != null) {
                    SysRolePermission role = new SysRolePermission();
                    role.setId(CommonUtil.getRandomUUID());
                    role.setMenuid(idArray[i]);
                    role.setRoleid(roleId);
                    this.sysRolePermissionService.add(role);
                }
            }
        }

    }

    public String deleteByEnterMenu(String enterPriseId) {
        String msg = null;

        try {
            int result = this.sysEnterpriseMenuMapper.deleteByEnterMenu(enterPriseId);
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
}
