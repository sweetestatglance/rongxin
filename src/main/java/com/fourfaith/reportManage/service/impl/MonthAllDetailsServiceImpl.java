//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service.impl;

import com.fourfaith.reportManage.dao.MonthAllDetailsMapper;
import com.fourfaith.reportManage.model.MonthAllDetails;
import com.fourfaith.reportManage.service.MonthAllDetailsService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("monthAllDetailsService")
public class MonthAllDetailsServiceImpl implements MonthAllDetailsService {
    protected Logger logger = Logger.getLogger(MonthAllDetailsServiceImpl.class);
    @Autowired
    private MonthAllDetailsMapper monthAllDetailsMapper;

    public MonthAllDetailsServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.monthAllDetailsMapper.deleteByPrimaryKey(id);
    }

    public int insert(MonthAllDetails record) {
        return this.monthAllDetailsMapper.insert(record);
    }

    public int insertSelective(MonthAllDetails record) {
        return this.monthAllDetailsMapper.insertSelective(record);
    }

    public MonthAllDetails selectByPrimaryKey(String id) {
        return this.monthAllDetailsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(MonthAllDetails record) {
        return this.monthAllDetailsMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MonthAllDetails record) {
        return this.monthAllDetailsMapper.updateByPrimaryKey(record);
    }

    public MonthAllDetails getDetail(Map<String, Object> params) {
        return this.monthAllDetailsMapper.getDetail(params);
    }

    public MonthAllDetails getYearDetail(Map<String, Object> params) {
        return this.monthAllDetailsMapper.getYearDetail(params);
    }

    public List<MonthAllDetails> getList(Map<String, Object> params) {
        return this.monthAllDetailsMapper.getList(params);
    }

    public MonthAllDetails getStatistic(Map<String, Object> params) {
        return this.monthAllDetailsMapper.getStatistic(params);
    }

    public int getStatistCount(Map<String, Object> params) {
        return this.monthAllDetailsMapper.getStatistCount(params);
    }

    public List<String> getStatistStcd(Map<String, Object> params) {
        return this.monthAllDetailsMapper.getStatistStcd(params);
    }
}
