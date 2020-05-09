//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.DeviceTaskMapper;
import com.fourfaith.siteManage.model.DeviceTask;
import com.fourfaith.siteManage.service.DeviceTaskService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deviceTaskService")
public class DeviceTaskServiceImpl implements DeviceTaskService {
    protected Logger logger = Logger.getLogger(DeviceTaskServiceImpl.class);
    @Autowired
    private DeviceTaskMapper deviceTaskMapper;

    public DeviceTaskServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.deviceTaskMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(DeviceTask record) {
        int result = this.deviceTaskMapper.insert(record);
        return result;
    }

    public int insertSelective(DeviceTask record) {
        int result = this.deviceTaskMapper.insertSelective(record);
        return result;
    }

    public DeviceTask selectByPrimaryKey(String id) {
        DeviceTask entity = this.deviceTaskMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(DeviceTask record) {
        int result = this.deviceTaskMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(DeviceTask record) {
        int result = this.deviceTaskMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.deviceTaskMapper.getCount(params);
        return result;
    }

    public List<DeviceTask> getList(Map<String, Object> params) {
        return this.deviceTaskMapper.getList(params);
    }

    public DeviceTask findById(String Id) {
        return this.deviceTaskMapper.selectByPrimaryKey(Id);
    }

    public int getTaskByStcdCount(Map<String, Object> params) {
        return this.deviceTaskMapper.getTaskByStcdCount(params);
    }

    public List<DeviceTask> getTaskByStcd(Map<String, Object> params) {
        return this.deviceTaskMapper.getTaskByStcd(params);
    }

    public Integer add(DeviceTask model) {
        return this.deviceTaskMapper.insertSelective(model);
    }

    public Integer updateByStatus(DeviceTask deviceTask) {
        return this.deviceTaskMapper.updateByStatus(deviceTask);
    }
}
