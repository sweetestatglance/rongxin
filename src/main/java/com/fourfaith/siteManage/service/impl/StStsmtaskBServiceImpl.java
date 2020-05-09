//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service.impl;

import com.fourfaith.reportManage.model.StEnterFactor;
import com.fourfaith.reportManage.service.StEnterFactorService;
import com.fourfaith.siteManage.dao.StStsmtaskBMapper;
import com.fourfaith.siteManage.model.StStsmtaskB;
import com.fourfaith.siteManage.service.StStsmtaskBService;
import com.fourfaith.utils.FactorName;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stStsmtaskBService")
public class StStsmtaskBServiceImpl implements StStsmtaskBService {
    protected Logger logger = Logger.getLogger(StStsmtaskBServiceImpl.class);
    @Autowired
    private StStsmtaskBMapper stStsmtaskBMapper;
    @Autowired
    private StEnterFactorService stEnterFactorService;

    public StStsmtaskBServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.stStsmtaskBMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(StStsmtaskB record) {
        int result = this.stStsmtaskBMapper.insert(record);
        return result;
    }

    public int insertSelective(StStsmtaskB record) {
        int result = this.stStsmtaskBMapper.insertSelective(record);
        return result;
    }

    public StStsmtaskB selectByPrimaryKey(String id) {
        StStsmtaskB entity = this.stStsmtaskBMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(StStsmtaskB record) {
        int result = this.stStsmtaskBMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(StStsmtaskB record) {
        int result = this.stStsmtaskBMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.stStsmtaskBMapper.getCount(params);
        return result;
    }

    public List<StStsmtaskB> getList(Map<String, Object> params) {
        return this.stStsmtaskBMapper.getList(params);
    }

    public StStsmtaskB findById(String Id) {
        return this.stStsmtaskBMapper.selectByPrimaryKey(Id);
    }

    public Integer add(StStsmtaskB record) {
        return this.stStsmtaskBMapper.insertSelective(record);
    }

    public Integer update(StStsmtaskB record) {
        return this.stStsmtaskBMapper.updateByPrimaryKeySelective(record);
    }

    public Integer delete(String id) {
        return this.stStsmtaskBMapper.deleteByPrimaryKey(id);
    }

    public List<String> getFactorFlagList(StStsmtaskB smtask, String enterId) {
        StEnterFactor enterFactor = this.stEnterFactorService.getByEnterId(enterId);
        List<String> factorFlaglist = new ArrayList();
        if (smtask != null) {
            FactorName[] var8;
            int var7 = (var8 = FactorName.values()).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                FactorName factor = var8[var6];
                int f = 0;

                try {
                    Field field = StStsmtaskB.class.getDeclaredField(factor.getFlag());
                    field.setAccessible(true);
                    f = (Integer)field.get(smtask);
                } catch (Exception var14) {
                    var14.printStackTrace();
                }

                int isView = 0;
                if (enterFactor != null) {
                    Field fields = null;

                    try {
                        fields = StEnterFactor.class.getDeclaredField(factor.getFlag());
                        fields.setAccessible(true);
                        isView = (Integer)fields.get(enterFactor);
                    } catch (Exception var13) {
                    }
                }

                if (f == 1 && isView == 1) {
                    factorFlaglist.add(factor.getFlag());
                }
            }
        }

        return factorFlaglist;
    }
}
