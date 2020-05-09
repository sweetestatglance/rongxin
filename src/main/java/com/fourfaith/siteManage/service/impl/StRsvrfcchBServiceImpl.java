//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StRsvrfcchBMapper;
import com.fourfaith.siteManage.model.StRsvrfcchB;
import com.fourfaith.siteManage.service.StRsvrfcchBService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stRsvrfcchBService")
public class StRsvrfcchBServiceImpl implements StRsvrfcchBService {
    protected Logger logger = Logger.getLogger(StRsvrfcchBServiceImpl.class);
    @Autowired
    private StRsvrfcchBMapper stRsvrfcchBMapper;

    public StRsvrfcchBServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.stRsvrfcchBMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(StRsvrfcchB record) {
        int result = this.stRsvrfcchBMapper.insert(record);
        return result;
    }

    public int insertSelective(StRsvrfcchB record) {
        int result = this.stRsvrfcchBMapper.insertSelective(record);
        return result;
    }

    public StRsvrfcchB selectByPrimaryKey(String id) {
        StRsvrfcchB entity = this.stRsvrfcchBMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(StRsvrfcchB record) {
        int result = this.stRsvrfcchBMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(StRsvrfcchB record) {
        int result = this.stRsvrfcchBMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.stRsvrfcchBMapper.getCount(params);
        return result;
    }

    public List<StRsvrfcchB> getList(Map<String, Object> params) {
        return this.stRsvrfcchBMapper.getList(params);
    }

    public StRsvrfcchB findById(String Id) {
        return this.stRsvrfcchBMapper.selectByPrimaryKey(Id);
    }

    public Integer add(StRsvrfcchB record) {
        return this.stRsvrfcchBMapper.insertSelective(record);
    }

    public Integer delete(String id) {
        return this.stRsvrfcchBMapper.deleteByPrimaryKey(id);
    }

    public Integer update(StRsvrfcchB record) {
        return this.stRsvrfcchBMapper.updateByPrimaryKeySelective(record);
    }

    public StRsvrfcchB findByStcd(String stcd) {
        return this.stRsvrfcchBMapper.findByStcd(stcd);
    }
}
