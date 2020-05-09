//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysEnterpriseMapper;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysRole;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserRole;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysRoleService;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.StringUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

@Service("sysEnterpriseService")
public class SysEnterpriseServiceImpl implements SysEnterpriseService {
    protected Logger logger = Logger.getLogger(SysEnterpriseServiceImpl.class);
    @Autowired
    private SysEnterpriseMapper sysEnterpriseMapper;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    public SysEnterpriseServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysEnterpriseMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysEnterprise record) {
        int result = this.sysEnterpriseMapper.insert(record);
        return result;
    }

    public int insertSelective(SysEnterprise record) {
        int result = this.sysEnterpriseMapper.insertSelective(record);
        return result;
    }

    public SysEnterprise selectByPrimaryKey(String id) {
        SysEnterprise entity = this.sysEnterpriseMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysEnterprise record) {
        int result = this.sysEnterpriseMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysEnterprise record) {
        int result = this.sysEnterpriseMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysEnterpriseMapper.getCount(params);
        return result;
    }

    public List<SysEnterprise> getList(Map<String, Object> params) {
        return this.sysEnterpriseMapper.getList(params);
    }

    public SysEnterprise findById(String Id) {
        return this.sysEnterpriseMapper.selectByPrimaryKey(Id);
    }

    public Integer update(SysEnterprise model) {
        return this.sysEnterpriseMapper.updateByPrimaryKeySelective(model);
    }

    public Integer add(SysEnterprise model) {
        return this.sysEnterpriseMapper.insertSelective(model);
    }

    public SysEnterprise getByEnterPriseCode(String code) {
        return this.sysEnterpriseMapper.getByEnterPriseCode(code);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void addCreateDefaultPermission(SysEnterprise enterPrise, HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        SysRole role = new SysRole();
        role.setId(CommonUtil.getRandomUUID());
        role.setEnterpriseid(enterPrise.getId());
        role.setRolecode(enterPrise.getEnterprisecode() + "_admin");
        role.setRolename(enterPrise.getEnterprisename() + "_" + requestContext.getMessage("admin"));
        role.setCreatetime(new Date());
        role.setUpdatetime(new Date());
        role.setEnabledstate(1);
        this.sysRoleService.add(role);
        SysUser user = new SysUser();
        user.setEnterpriseid(enterPrise.getId());
        user.setUsercode(enterPrise.getEnterprisecode() + "_admin");
        user.setUsername(enterPrise.getEnterprisecode() + "_admin");
        user.setId(CommonUtil.getRandomUUID());
        String salt = StringUtils.getRandomStr(8, false);
        user.setUserpwdsalt(salt);
        user.setUserpwd(StringUtils.encryptMd5("123456" + salt));
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setEnabledstate(1);
        this.sysUserService.add(user);
        SysUserRole userRole = new SysUserRole();
        userRole.setId(CommonUtil.getRandomUUID());
        userRole.setRoleid(role.getId());
        userRole.setUserid(user.getId());
        this.sysUserRoleService.add(userRole);
    }
}
