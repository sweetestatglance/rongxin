//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StAlarmInfo;
import java.util.List;
import java.util.Map;

public interface StAlarmInfoMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StAlarmInfo var1);

    int insertSelective(StAlarmInfo var1);

    StAlarmInfo selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StAlarmInfo var1);

    int updateByPrimaryKey(StAlarmInfo var1);

    List<StAlarmInfo> getAlarmList(Map<String, Object> var1);

    Integer getCount(Map<String, Object> var1);

    Integer getTodayCount(Map<String, Object> var1);

    List<StAlarmInfo> getSevenALarmList(Map<String, Object> var1);

    int updateHasSolved(Map<String, Object> var1);
}
