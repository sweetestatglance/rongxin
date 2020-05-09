//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service.impl;

import com.fourfaith.reportManage.dao.StDeviceFactorMapper;
import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.StDeviceFactorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stDeviceFactorService")
public class StDeviceFactorServiceImpl implements StDeviceFactorService {
    protected Logger logger = Logger.getLogger(StDeviceFactorServiceImpl.class);
    @Autowired
    private StDeviceFactorMapper stDeviceFactorMapper;

    public StDeviceFactorServiceImpl() {
    }

    public int deleteByPrimaryKey(String id) {
        int result = this.stDeviceFactorMapper.deleteByPrimaryKey(id);
        return result;
    }

    public int insert(StDeviceFactor record) {
        int result = this.stDeviceFactorMapper.insert(record);
        return result;
    }

    public int insertSelective(StDeviceFactor record) {
        int result = this.stDeviceFactorMapper.insertSelective(record);
        return result;
    }

    public StDeviceFactor selectByPrimaryKey(String id) {
        StDeviceFactor entity = this.stDeviceFactorMapper.selectByPrimaryKey(id);
        return entity;
    }

    public int updateByPrimaryKeySelective(StDeviceFactor record) {
        int result = this.stDeviceFactorMapper.updateByPrimaryKeySelective(record);
        return result;
    }

    public int updateByPrimaryKey(StDeviceFactor record) {
        int result = this.stDeviceFactorMapper.updateByPrimaryKey(record);
        return result;
    }

    public Integer getCount(Map<String, Object> params) {
        int result = this.stDeviceFactorMapper.getCount(params);
        return result;
    }

    public List<StDeviceFactor> getList(Map<String, Object> params) {
        return this.stDeviceFactorMapper.getList(params);
    }

    public StDeviceFactor findById(String Id) {
        return this.stDeviceFactorMapper.findById(Id);
    }

    public StDeviceFactor findByStcd(String stcd) {
        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        List<StDeviceFactor> list = this.stDeviceFactorMapper.getDfList(params);
        return list != null && list.size() > 0 ? (StDeviceFactor)list.get(0) : null;
    }

    public String deleteStcd(String stcd) {
        String msg = null;

        try {
            int result = this.stDeviceFactorMapper.deleteStcd(stcd);
            if (result > 0) {
                msg = "删除成功";
            } else {
                msg = "删除失败";
            }

            this.logger.info(msg);
        } catch (Exception var4) {
            var4.printStackTrace();
            msg = "删除失败";
            this.logger.error(msg + var4.getMessage());
        }

        return msg;
    }

    public Double getMaxPj(Map<String, Object> params) {
        return this.stDeviceFactorMapper.getMaxPj(params);
    }
}
