//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.DeviceTask;
import java.util.List;
import java.util.Map;

public interface DeviceTaskMapper {
    int deleteByPrimaryKey(String var1);

    int insert(DeviceTask var1);

    int insertSelective(DeviceTask var1);

    DeviceTask selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(DeviceTask var1);

    int updateByPrimaryKey(DeviceTask var1);

    Integer getCount(Map<String, Object> var1);

    List<DeviceTask> getList(Map<String, Object> var1);

    int getTaskByStcdCount(Map<String, Object> var1);

    List<DeviceTask> getTaskByStcd(Map<String, Object> var1);

    int updateByStatus(DeviceTask var1);
}
