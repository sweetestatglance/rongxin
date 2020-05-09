//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysUserFactorMapper;
import com.fourfaith.sysManage.model.SysUserFactor;
import com.fourfaith.sysManage.service.SysUserFactorService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysUserFactorService")
public class SysUserFactorServiceImpl implements SysUserFactorService {
    protected Logger logger = Logger.getLogger(SysUserFactorServiceImpl.class);
    @Autowired
    private SysUserFactorMapper sysUserFactorMapper;

    public SysUserFactorServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.sysUserFactorMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysUserFactor record) {
        return this.sysUserFactorMapper.insert(record);
    }

    public int insertSelective(SysUserFactor record) {
        return this.sysUserFactorMapper.insertSelective(record);
    }

    public SysUserFactor selectByPrimaryKey(String id) {
        return this.sysUserFactorMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysUserFactor record) {
        return this.sysUserFactorMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysUserFactor record) {
        return this.sysUserFactorMapper.updateByPrimaryKey(record);
    }

    public List<SysUserFactor> getList(Map<String, Object> params) {
        return this.sysUserFactorMapper.getList(params);
    }

    public int deleteByUserId(String userid) {
        return this.sysUserFactorMapper.deleteByUserId(userid);
    }

    public int delete(Map<String, Object> params) {
        return this.sysUserFactorMapper.delete(params);
    }
}
