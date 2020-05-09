//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StAlarmConfigureMapper;
import com.fourfaith.siteManage.model.StAlarmConfigure;
import com.fourfaith.siteManage.service.StAlarmConfigureService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stAlarmConfigureService")
public class StAlarmConfigureServiceImpl implements StAlarmConfigureService {
    protected Logger logger = Logger.getLogger(StAlarmConfigureServiceImpl.class);
    @Autowired
    private StAlarmConfigureMapper stAlarmConfigureMapper;

    public StAlarmConfigureServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.stAlarmConfigureMapper.deleteByPrimaryKey(id);
    }

    public int insert(StAlarmConfigure record) {
        return this.stAlarmConfigureMapper.insert(record);
    }

    public int insertSelective(StAlarmConfigure record) {
        return this.stAlarmConfigureMapper.insertSelective(record);
    }

    public StAlarmConfigure selectByPrimaryKey(String id) {
        return this.stAlarmConfigureMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StAlarmConfigure record) {
        return this.stAlarmConfigureMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StAlarmConfigure record) {
        return this.stAlarmConfigureMapper.updateByPrimaryKey(record);
    }

    public int getCount(Map<String, Object> params) {
        return this.stAlarmConfigureMapper.getCount(params);
    }

    public List<StAlarmConfigure> getList(Map<String, Object> params) {
        return this.stAlarmConfigureMapper.getList(params);
    }

    public StAlarmConfigure findByStcd(String stcd) {
        return this.stAlarmConfigureMapper.findByStcd(stcd);
    }

    public List<StAlarmConfigure> getByPerson(String personId) {
        return this.stAlarmConfigureMapper.getByPerson(personId);
    }
}
