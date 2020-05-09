//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysUserAnnounceMapper;
import com.fourfaith.sysManage.model.SysUserAnnounce;
import com.fourfaith.sysManage.service.SysUserAnnounceService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysUserAnnounceService")
public class SysUserAnnounceServiceImpl implements SysUserAnnounceService {
    protected Logger logger = Logger.getLogger(SysUserAnnounceServiceImpl.class);
    @Autowired
    private SysUserAnnounceMapper sysUserAnnounceMapper;

    public SysUserAnnounceServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysUserAnnounceMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysUserAnnounce record) {
        int result = this.sysUserAnnounceMapper.insert(record);
        return result;
    }

    public int insertSelective(SysUserAnnounce record) {
        int result = this.sysUserAnnounceMapper.insertSelective(record);
        return result;
    }

    public SysUserAnnounce selectByPrimaryKey(String id) {
        SysUserAnnounce entity = this.sysUserAnnounceMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysUserAnnounce record) {
        int result = this.sysUserAnnounceMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysUserAnnounce record) {
        int result = this.sysUserAnnounceMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysUserAnnounceMapper.getCount(params);
        return result;
    }

    public List<SysUserAnnounce> getList(Map<String, Object> params) {
        return this.sysUserAnnounceMapper.getList(params);
    }

    public SysUserAnnounce findById(String Id) {
        return this.sysUserAnnounceMapper.findById(Id);
    }

    public Integer add(SysUserAnnounce model) {
        return this.sysUserAnnounceMapper.insertSelective(model);
    }

    public Integer deleteByNoticeId(String noticeid) {
        return this.sysUserAnnounceMapper.deleteByNoticeId(noticeid);
    }

    public SysUserAnnounce findByUserIdAndAnnId(Map<String, Object> params) {
        return this.sysUserAnnounceMapper.findByUserIdAndAnnId(params);
    }
}
