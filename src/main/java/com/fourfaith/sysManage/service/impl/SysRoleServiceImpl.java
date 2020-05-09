//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysRoleMapper;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.sysManage.service.SysRolePermissionService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.utils.AjaxJson;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    protected Logger logger = Logger.getLogger(SysRoleServiceImpl.class);
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    public SysRoleServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysRoleMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysRole record) {
        int result = this.sysRoleMapper.insert(record);
        return result;
    }

    public Integer delete(String id) {
        return this.sysRoleMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(SysRole record) {
        int result = this.sysRoleMapper.insertSelective(record);
        return result;
    }

    public SysRole selectByPrimaryKey(String id) {
        SysRole entity = this.sysRoleMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysRole record) {
        int result = this.sysRoleMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysRole record) {
        int result = this.sysRoleMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysRoleMapper.getCount(params);
        return result;
    }

    public List<SysRole> getList(Map<String, Object> params) {
        return this.sysRoleMapper.getList(params);
    }

    public SysRole findById(String Id) {
        return this.sysRoleMapper.selectByPrimaryKey(Id);
    }

    public Integer add(SysRole model) {
        return this.sysRoleMapper.insertSelective(model);
    }

    public Integer update(SysRole model) {
        return this.sysRoleMapper.updateByPrimaryKeySelective(model);
    }

    public SysRole getByRoleId(Map<String, Object> map) {
        return this.sysRoleMapper.getByRoleId(map);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson deleteRole(String items, HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajaxJson = new AjaxJson();
        String logContent = "";

        try {
            if (items != null) {
                String[] itemArray = items.split(",");
                String[] var10 = itemArray;
                int var9 = itemArray.length;

                for(int var8 = 0; var8 < var9; ++var8) {
                    String item = var10[var8];
                    SysRole role = this.findById(item);
                    logContent = logContent + requestContext.getMessage("roleDeleteLog", new Object[]{"[" + role.getRolename() + "]"}) + ",";
                    this.delete(item);
                    this.sysUserRoleService.deleteByRoleId(item);
                    this.sysRoleAreaService.deleteRoleId(item);
                    this.sysRolePermissionService.deleteByRoleMenu(item);
                }

                ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
                ajaxJson.setSuccess(true);
                ajaxJson.setObj(logContent);
            }
        } catch (Exception var13) {
            var13.printStackTrace();
            ajaxJson.setMsg(requestContext.getMessage("optFailExcept") + "：" + var13.getMessage());
            ajaxJson.setSuccess(false);
            ajaxJson.setObj(logContent);
        }

        return ajaxJson;
    }
}
