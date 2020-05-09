//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service.impl;

import com.fourfaith.reportManage.dao.StEnterFactorMapper;
import com.fourfaith.reportManage.model.StEnterFactor;
import com.fourfaith.reportManage.service.StEnterFactorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stEnterFactorService")
public class StEnterFactorServiceImpl implements StEnterFactorService {
    protected Logger logger = Logger.getLogger(StEnterFactorServiceImpl.class);
    @Autowired
    private StEnterFactorMapper stEnterFactorMapper;

    public StEnterFactorServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.stEnterFactorMapper.deleteByPrimaryKey(id);
    }

    public int insert(StEnterFactor record) {
        return this.stEnterFactorMapper.insert(record);
    }

    public int insertSelective(StEnterFactor record) {
        return this.stEnterFactorMapper.insertSelective(record);
    }

    public StEnterFactor selectByPrimaryKey(String id) {
        return this.stEnterFactorMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StEnterFactor record) {
        return this.stEnterFactorMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StEnterFactor record) {
        return this.stEnterFactorMapper.updateByPrimaryKey(record);
    }

    public StEnterFactor getByEnterId(String enterId) {
        return this.stEnterFactorMapper.getByEnterId(enterId);
    }
}
