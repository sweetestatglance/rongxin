//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StAlarmPerson;
import java.util.List;
import java.util.Map;

public interface StAlarmPersonMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StAlarmPerson var1);

    int insertSelective(StAlarmPerson var1);

    StAlarmPerson selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StAlarmPerson var1);

    int updateByPrimaryKey(StAlarmPerson var1);

    List<StAlarmPerson> getList(Map<String, Object> var1);

    int getCount(Map<String, Object> var1);
}
