//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.imgManage.service.impl;

import com.fourfaith.imgManage.dao.StImgMonitMapper;
import com.fourfaith.imgManage.model.StImgMonit;
import com.fourfaith.imgManage.service.StImgMonitService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stImgMonitService")
public class StImgMonitServiceImpl implements StImgMonitService {
    protected Logger logger = Logger.getLogger(StImgMonitServiceImpl.class);
    @Autowired
    private StImgMonitMapper stImgMonitMapper;

    public StImgMonitServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        return this.stImgMonitMapper.deleteByPrimaryKey(id);
    }

    public int insert(StImgMonit record) {
        return this.stImgMonitMapper.insert(record);
    }

    public int insertSelective(StImgMonit record) {
        return this.stImgMonitMapper.insertSelective(record);
    }

    public StImgMonit selectByPrimaryKey(String id) {
        return this.stImgMonitMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StImgMonit record) {
        return this.stImgMonitMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StImgMonit record) {
        return this.stImgMonitMapper.updateByPrimaryKey(record);
    }

    public List<StImgMonit> getImgList(Map<String, Object> params) {
        return this.stImgMonitMapper.getImgList(params);
    }

    public Integer getImgCount(Map<String, Object> params) {
        return this.stImgMonitMapper.getImgCount(params);
    }

    public StImgMonit getStcd(String stcd) {
        return this.stImgMonitMapper.getStcd(stcd);
    }
}
