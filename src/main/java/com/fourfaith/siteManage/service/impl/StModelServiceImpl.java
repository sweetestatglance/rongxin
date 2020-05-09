//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StModelMapper;
import com.fourfaith.siteManage.model.StModel;
import com.fourfaith.siteManage.service.StModelService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stModelService")
public class StModelServiceImpl implements StModelService {
    protected Logger logger = Logger.getLogger(StModelServiceImpl.class);
    @Autowired
    private StModelMapper stModelMapper;

    public StModelServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.stModelMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(StModel record) {
        int result = this.stModelMapper.insert(record);
        return result;
    }

    public int insertSelective(StModel record) {
        int result = this.stModelMapper.insertSelective(record);
        return result;
    }

    public StModel selectByPrimaryKey(String id) {
        StModel entity = this.stModelMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(StModel record) {
        int result = this.stModelMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(StModel record) {
        int result = this.stModelMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.stModelMapper.getCount(params);
        return result;
    }

    public List<StModel> getList(Map<String, Object> params) {
        return this.stModelMapper.getList(params);
    }

    public StModel findById(String Id) {
        return this.stModelMapper.selectByPrimaryKey(Id);
    }

    public Integer add(StModel record) {
        return this.stModelMapper.insertSelective(record);
    }

    public Integer update(StModel record) {
        return this.stModelMapper.updateByPrimaryKeySelective(record);
    }

    public StModel getNm(Map<String, Object> map) {
        return this.stModelMapper.getNm(map);
    }

    public Integer delete(String id) {
        return this.stModelMapper.deleteByPrimaryKey(id);
    }
}
