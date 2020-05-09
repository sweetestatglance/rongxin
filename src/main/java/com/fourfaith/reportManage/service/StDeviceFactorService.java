//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service;

import com.fourfaith.reportManage.model.StDeviceFactor;
import java.util.List;
import java.util.Map;

public interface StDeviceFactorService {
    int deleteByPrimaryKey(String var1);

    int insert(StDeviceFactor var1);

    int insertSelective(StDeviceFactor var1);

    StDeviceFactor selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StDeviceFactor var1);

    int updateByPrimaryKey(StDeviceFactor var1);

    Integer getCount(Map<String, Object> var1);

    List<StDeviceFactor> getList(Map<String, Object> var1);

    Double getMaxPj(Map<String, Object> var1);

    StDeviceFactor findById(String var1);

    StDeviceFactor findByStcd(String var1);

    String deleteStcd(String var1);
}
