//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StAlarmSmsSendLog;
import java.util.List;
import java.util.Map;

public interface StAlarmSmsSendLogMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StAlarmSmsSendLog var1);

    int insertSelective(StAlarmSmsSendLog var1);

    StAlarmSmsSendLog selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StAlarmSmsSendLog var1);

    int updateByPrimaryKey(StAlarmSmsSendLog var1);

    List<StAlarmSmsSendLog> getList(Map<String, Object> var1);

    int getCount(Map<String, Object> var1);
}
