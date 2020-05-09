//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysUserMapper;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.AjaxJson;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    protected Logger logger = Logger.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    public SysUserServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysUserMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysUser record) {
        int result = this.sysUserMapper.insert(record);
        return result;
    }

    public int insertSelective(SysUser record) {
        int result = this.sysUserMapper.insertSelective(record);
        return result;
    }

    public SysUser selectByPrimaryKey(String id) {
        SysUser entity = this.sysUserMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysUser record) {
        int result = this.sysUserMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysUser record) {
        int result = this.sysUserMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysUserMapper.getCount(params);
        return result;
    }

    public List<SysUser> getList(Map<String, Object> params) {
        return this.sysUserMapper.getList(params);
    }

    public SysUser findById(String Id) {
        return this.sysUserMapper.selectByPrimaryKey(Id);
    }

    public SysUser findByUserName(String username) {
        return this.sysUserMapper.findByUserName(username);
    }

    public Integer add(SysUser model) {
        return this.sysUserMapper.insertSelective(model);
    }

    public Integer update(SysUser model) {
        return this.sysUserMapper.updateByPrimaryKeySelective(model);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public AjaxJson deleteUser(String items, HttpServletRequest request) {
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
                    SysUser user = this.findById(item);
                    logContent = logContent + logContent + requestContext.getMessage("userDeleteLog", new Object[]{"[" + user.getUsername() + "]"}) + ",";
                    this.delete(item);
                    List<String> userRoleList = this.sysUserRoleService.getUserRoleId(item);
                    if (userRoleList != null && userRoleList.size() > 0) {
                        for(int i = 0; i < userRoleList.size(); ++i) {
                            String userRoleId = (String)userRoleList.get(i);
                            this.sysUserRoleService.delete(userRoleId);
                        }
                    }

                    ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
                    ajaxJson.setSuccess(true);
                    ajaxJson.setObj(logContent);
                }
            }
        } catch (Exception var16) {
            var16.printStackTrace();
            ajaxJson.setMsg(requestContext.getMessage("optFailExcept") + "：" + var16.getMessage());
            ajaxJson.setSuccess(false);
            ajaxJson.setObj(logContent);
        }

        return ajaxJson;
    }

    public Integer delete(String id) {
        return this.sysUserMapper.deleteByPrimaryKey(id);
    }

    public Integer moveUser(SysUser model) {
        return this.sysUserMapper.updateByPrimaryKey(model);
    }
}
