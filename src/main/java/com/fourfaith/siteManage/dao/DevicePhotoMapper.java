//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.DevicePhoto;
import java.util.List;
import java.util.Map;

public interface DevicePhotoMapper {
    int deleteByPrimaryKey(String var1);

    int insert(DevicePhoto var1);

    int insertSelective(DevicePhoto var1);

    DevicePhoto selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(DevicePhoto var1);

    int updateByPrimaryKey(DevicePhoto var1);

    Integer getCount(Map<String, Object> var1);

    List<DevicePhoto> getList(Map<String, Object> var1);

    DevicePhoto findById(String var1);
}
