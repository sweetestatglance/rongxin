//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.dao;

import com.fourfaith.reportManage.model.MonthAllDetails;
import java.util.List;
import java.util.Map;

public interface MonthAllDetailsMapper {
    int deleteByPrimaryKey(String var1);

    int insert(MonthAllDetails var1);

    int insertSelective(MonthAllDetails var1);

    MonthAllDetails selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(MonthAllDetails var1);

    int updateByPrimaryKey(MonthAllDetails var1);

    MonthAllDetails getDetail(Map<String, Object> var1);

    MonthAllDetails getYearDetail(Map<String, Object> var1);

    List<MonthAllDetails> getList(Map<String, Object> var1);

    MonthAllDetails getStatistic(Map<String, Object> var1);

    int getStatistCount(Map<String, Object> var1);

    List<String> getStatistStcd(Map<String, Object> var1);
}
