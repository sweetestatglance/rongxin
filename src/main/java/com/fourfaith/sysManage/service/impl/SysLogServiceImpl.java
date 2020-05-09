//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service.impl;

import com.fourfaith.sysManage.dao.SysLogMapper;
import com.fourfaith.sysManage.model.SysLog;
import com.fourfaith.sysManage.service.SysLogService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    protected Logger logger = Logger.getLogger(SysLogServiceImpl.class);
    @Autowired
    private SysLogMapper sysLogMapper;

    public SysLogServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.sysLogMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(SysLog record) {
        int result = this.sysLogMapper.insert(record);
        return result;
    }

    public int insertSelective(SysLog record) {
        int result = this.sysLogMapper.insertSelective(record);
        return result;
    }

    public SysLog selectByPrimaryKey(String id) {
        SysLog entity = this.sysLogMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(SysLog record) {
        int result = this.sysLogMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(SysLog record) {
        int result = this.sysLogMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.sysLogMapper.getCount(params);
        return result;
    }

    public List<SysLog> getList(Map<String, Object> params) {
        return this.sysLogMapper.getList(params);
    }

    public SysLog findById(String Id) {
        return this.sysLogMapper.findById(Id);
    }

    public Integer add(SysLog model) {
        return this.sysLogMapper.insertSelective(model);
    }
}
