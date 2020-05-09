//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StAddvcdDMapper;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.service.StAddvcdDService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stAddvcdDService")
public class StAddvcdDServiceImpl implements StAddvcdDService {
    protected Logger logger = Logger.getLogger(StAddvcdDServiceImpl.class);
    @Autowired
    private StAddvcdDMapper stAddvcdDMapper;

    public StAddvcdDServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.stAddvcdDMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(StAddvcdD record) {
        int result = this.stAddvcdDMapper.insert(record);
        return result;
    }

    public int insertSelective(StAddvcdD record) {
        int result = this.stAddvcdDMapper.insertSelective(record);
        return result;
    }

    public StAddvcdD selectByPrimaryKey(String id) {
        StAddvcdD entity = this.stAddvcdDMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(StAddvcdD record) {
        int result = this.stAddvcdDMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(StAddvcdD record) {
        int result = this.stAddvcdDMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.stAddvcdDMapper.getCount(params);
        return result;
    }

    public List<StAddvcdD> getList(Map<String, Object> params) {
        return this.stAddvcdDMapper.getList(params);
    }

    public StAddvcdD findById(String Id) {
        return this.stAddvcdDMapper.selectByPrimaryKey(Id);
    }

    public List<StAddvcdD> getListByIdList(List<String> idList) {
        Map<String, Object> params = new HashMap();
        params.put("idList", idList);
        return this.stAddvcdDMapper.getList(params);
    }

    public Integer add(StAddvcdD model) {
        return this.stAddvcdDMapper.insertSelective(model);
    }

    public Integer update(StAddvcdD model) {
        return this.stAddvcdDMapper.updateByPrimaryKeySelective(model);
    }

    public StAddvcdD getAddvnm(Map<String, Object> map) {
        return this.stAddvcdDMapper.getAddvnm(map);
    }

    public List<StAddvcdD> getChildAddvcdD(String faddvcd) {
        return this.stAddvcdDMapper.getChildAddvcdD(faddvcd);
    }

    public int isParent(String faddvcd) {
        return this.stAddvcdDMapper.isParent(faddvcd);
    }

    public Integer delete(String id) {
        return this.stAddvcdDMapper.deleteByPrimaryKey(id);
    }
}
