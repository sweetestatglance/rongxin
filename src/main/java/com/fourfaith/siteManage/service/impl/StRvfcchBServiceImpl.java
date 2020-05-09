//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.siteManage.dao.StRvfcchBMapper;
import com.fourfaith.siteManage.model.StRvfcchB;
import com.fourfaith.siteManage.service.StRvfcchBService;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stRvfcchBService")
public class StRvfcchBServiceImpl implements StRvfcchBService {
    protected Logger logger = Logger.getLogger(StRvfcchBServiceImpl.class);
    @Autowired
    private StRvfcchBMapper stRvfcchBMapper;

    public StRvfcchBServiceImpl() {
    }

    public Integer add(StRvfcchB model) {
        return this.stRvfcchBMapper.insertSelective(model);
    }

    public List<StRvfcchB> getList(Map<String, Object> params) {
        return this.stRvfcchBMapper.getList(params);
    }

    public Integer delete(String id) {
        return this.stRvfcchBMapper.deleteByPrimaryKey(id);
    }

    public StRvfcchB findById(String id) {
        return this.stRvfcchBMapper.selectByPrimaryKey(id);
    }

    public Integer update(StRvfcchB model) {
        return this.stRvfcchBMapper.updateByPrimaryKeySelective(model);
    }

    public StRvfcchB findByStcd(String stcd) {
        return this.stRvfcchBMapper.findByStcd(stcd);
    }
}
