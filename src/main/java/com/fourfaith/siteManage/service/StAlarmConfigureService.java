//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service;

import com.fourfaith.siteManage.model.StAlarmConfigure;
import java.util.List;
import java.util.Map;

public interface StAlarmConfigureService {
    int deleteByPrimaryKey(String var1);

    int insert(StAlarmConfigure var1);

    int insertSelective(StAlarmConfigure var1);

    StAlarmConfigure selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StAlarmConfigure var1);

    int updateByPrimaryKey(StAlarmConfigure var1);

    int getCount(Map<String, Object> var1);

    List<StAlarmConfigure> getList(Map<String, Object> var1);

    StAlarmConfigure findByStcd(String var1);

    List<StAlarmConfigure> getByPerson(String var1);
}
