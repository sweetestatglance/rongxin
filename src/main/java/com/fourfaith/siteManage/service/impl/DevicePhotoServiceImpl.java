//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.DevicePhotoMapper;
import com.fourfaith.siteManage.model.DevicePhoto;
import com.fourfaith.siteManage.service.DevicePhotoService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("devicePhotoService")
public class DevicePhotoServiceImpl implements DevicePhotoService {
    protected Logger logger = Logger.getLogger(DevicePhotoServiceImpl.class);
    @Autowired
    private DevicePhotoMapper devicePhotoMapper;

    public DevicePhotoServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.devicePhotoMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(DevicePhoto record) {
        int result = this.devicePhotoMapper.insert(record);
        return result;
    }

    public int insertSelective(DevicePhoto record) {
        int result = this.devicePhotoMapper.insertSelective(record);
        return result;
    }

    public DevicePhoto selectByPrimaryKey(String id) {
        DevicePhoto entity = this.devicePhotoMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(DevicePhoto record) {
        int result = this.devicePhotoMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(DevicePhoto record) {
        int result = this.devicePhotoMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.devicePhotoMapper.getCount(params);
        return result;
    }

    public List<DevicePhoto> getList(Map<String, Object> params) {
        return this.devicePhotoMapper.getList(params);
    }

    public DevicePhoto findById(String Id) {
        return this.devicePhotoMapper.selectByPrimaryKey(Id);
    }

    public Integer update(DevicePhoto model) {
        return this.devicePhotoMapper.updateByPrimaryKeySelective(model);
    }
}
