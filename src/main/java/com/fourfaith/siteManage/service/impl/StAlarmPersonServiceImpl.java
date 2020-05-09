//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StAlarmPersonMapper;
import com.fourfaith.siteManage.model.StAlarmPerson;
import com.fourfaith.siteManage.service.StAlarmPersonService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stAlarmPersonService")
public class StAlarmPersonServiceImpl implements StAlarmPersonService {
    protected Logger logger = Logger.getLogger(StAlarmPersonServiceImpl.class);
    @Autowired
    private StAlarmPersonMapper stAlarmPersonMapper;

    public StAlarmPersonServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.stAlarmPersonMapper.deleteByPrimaryKey(id);
    }

    public int insert(StAlarmPerson record) {
        return this.stAlarmPersonMapper.insert(record);
    }

    public int insertSelective(StAlarmPerson record) {
        return this.stAlarmPersonMapper.insertSelective(record);
    }

    public StAlarmPerson selectByPrimaryKey(String id) {
        return this.stAlarmPersonMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StAlarmPerson record) {
        return this.stAlarmPersonMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StAlarmPerson record) {
        return this.stAlarmPersonMapper.updateByPrimaryKey(record);
    }

    public List<StAlarmPerson> getList(Map<String, Object> params) {
        return this.stAlarmPersonMapper.getList(params);
    }

    public int getCount(Map<String, Object> params) {
        return this.stAlarmPersonMapper.getCount(params);
    }
}
