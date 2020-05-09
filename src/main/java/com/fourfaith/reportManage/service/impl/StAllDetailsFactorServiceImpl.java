//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service.impl;

import com.fourfaith.reportManage.dao.StAllDetailsFactorMapper;
import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stAllDetailsFactorService")
public class StAllDetailsFactorServiceImpl implements StAllDetailsFactorService {
    protected Logger logger = Logger.getLogger(StAllDetailsFactorServiceImpl.class);
    @Autowired
    private StAllDetailsFactorMapper stAllDetailsFactorMapper;

    public StAllDetailsFactorServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.stAllDetailsFactorMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(StAllDetailsFactor record) {
        int result = this.stAllDetailsFactorMapper.insert(record);
        return result;
    }

    public int insertSelective(StAllDetailsFactor record) {
        int result = this.stAllDetailsFactorMapper.insertSelective(record);
        return result;
    }

    public StAllDetailsFactor selectByPrimaryKey(String id) {
        StAllDetailsFactor entity = this.stAllDetailsFactorMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(StAllDetailsFactor record) {
        int result = this.stAllDetailsFactorMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(StAllDetailsFactor record) {
        int result = this.stAllDetailsFactorMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.stAllDetailsFactorMapper.getCount(params);
        return result;
    }

    public List<StAllDetailsFactor> getList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getList(params);
    }

    public StAllDetailsFactor findById(String Id) {
        return this.stAllDetailsFactorMapper.findById(Id);
    }

    public List<StAllDetailsFactor> getStatistList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getStatistList(params);
    }

    public Integer getStatistCount(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getStatistCount(params);
    }

    public Integer getFactorCount(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getFactorCount(params);
    }

    public List<StAllDetailsFactor> getFactorList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getFactorList(params);
    }

    public List<StDeviceFactor> getReportStaticList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getReportStaticList(params);
    }

    public StDeviceFactor getReportStatic(Map<String, Object> params) {
        StDeviceFactor model = new StDeviceFactor();
        List<StDeviceFactor> list = this.stAllDetailsFactorMapper.getReportStaticList(params);
        if (list != null && list.size() > 0) {
            model = (StDeviceFactor)list.get(0);
        }

        return model;
    }

    public StAllDetailsFactor getLastest(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getLastest(params);
    }

    public StAllDetailsFactor getWaterQualityStatis(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getWaterQualityStatis(params);
    }

    public StAllDetailsFactor getDetail(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getDetail(params);
    }

    public int getTableExist(String tableName) {
        return this.stAllDetailsFactorMapper.getTableExist(tableName);
    }

    public int getDaySzCount(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getDaySzCount(params);
    }

    public List<StAllDetailsFactor> getDaySzList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getDaySzList(params);
    }

    public int getFactorStatistCount(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getFactorStatistCount(params);
    }

    public List<StAllDetailsFactor> getFactorStatistList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getFactorStatistList(params);
    }

    public Double getMaxPj(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getMaxPj(params);
    }

    public int getAlmanacReportCount(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getAlmanacReportCount(params);
    }

    public List<StAllDetailsFactor> getAlmanacReportList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getAlmanacReportList(params);
    }

    public List<StAllDetailsFactor> getHistoryList(Map<String, Object> params) {
        return this.stAllDetailsFactorMapper.getHistoryList(params);
    }
}
