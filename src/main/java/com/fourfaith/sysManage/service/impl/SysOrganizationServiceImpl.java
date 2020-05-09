//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysOrganizationMapper;
import com.fourfaith.sysManage.model.SysOrganization;
import com.fourfaith.sysManage.service.SysOrganizationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysOrganizationService")
public class SysOrganizationServiceImpl implements SysOrganizationService {
    protected Logger logger = Logger.getLogger(SysOrganizationServiceImpl.class);
    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;

    public SysOrganizationServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysOrganizationMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysOrganization record) {
        int result = this.sysOrganizationMapper.insert(record);
        return result;
    }

    public int insertSelective(SysOrganization record) {
        int result = this.sysOrganizationMapper.insertSelective(record);
        return result;
    }

    public SysOrganization selectByPrimaryKey(String id) {
        SysOrganization entity = this.sysOrganizationMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysOrganization record) {
        int result = this.sysOrganizationMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysOrganization record) {
        int result = this.sysOrganizationMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysOrganizationMapper.getCount(params);
        return result;
    }

    public List<SysOrganization> getList(Map<String, Object> params) {
        return this.sysOrganizationMapper.getList(params);
    }

    public SysOrganization findById(String Id) {
        return this.sysOrganizationMapper.selectByPrimaryKey(Id);
    }

    public List<String> getAllChildIdList(String id) {
        return this.getChildIdList(id);
    }

    public List<String> getChildIdList(String orgId) {
        List<String> orgIdList = new ArrayList();
        Map<String, Object> params = new HashMap();
        params.put("enabledstate", 1);
        params.put("pid", orgId);
        List<SysOrganization> orgList = this.getList(params);
        if (orgList != null && orgList.size() > 0) {
            Iterator var6 = orgList.iterator();

            while(var6.hasNext()) {
                SysOrganization org = (SysOrganization)var6.next();
                orgIdList.addAll(this.getChildIdList(org.getId()));
            }
        } else {
            orgIdList.add(orgId);
        }

        return orgIdList;
    }

    public Integer add(SysOrganization model) {
        return this.sysOrganizationMapper.insertSelective(model);
    }

    public Integer update(SysOrganization model) {
        return this.sysOrganizationMapper.updateByPrimaryKeySelective(model);
    }

    public Integer delete(String id) {
        return this.sysOrganizationMapper.deleteByPrimaryKey(id);
    }

    public SysOrganization getByOrgan(Map<String, Object> params) {
        return this.sysOrganizationMapper.getByOrgan(params);
    }
}
