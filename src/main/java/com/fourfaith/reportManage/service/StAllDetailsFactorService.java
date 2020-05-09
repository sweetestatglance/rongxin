//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.service;

import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.model.StDeviceFactor;
import java.util.List;
import java.util.Map;

public interface StAllDetailsFactorService {
    int deleteByPrimaryKey(String var1);

    int insert(StAllDetailsFactor var1);

    int insertSelective(StAllDetailsFactor var1);

    StAllDetailsFactor selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StAllDetailsFactor var1);

    int updateByPrimaryKey(StAllDetailsFactor var1);

    Integer getCount(Map<String, Object> var1);

    List<StAllDetailsFactor> getList(Map<String, Object> var1);

    List<StAllDetailsFactor> getHistoryList(Map<String, Object> var1);

    StAllDetailsFactor findById(String var1);

    List<StAllDetailsFactor> getStatistList(Map<String, Object> var1);

    Integer getStatistCount(Map<String, Object> var1);

    Integer getFactorCount(Map<String, Object> var1);

    List<StAllDetailsFactor> getFactorList(Map<String, Object> var1);

    StDeviceFactor getReportStatic(Map<String, Object> var1);

    List<StDeviceFactor> getReportStaticList(Map<String, Object> var1);

    StAllDetailsFactor getLastest(Map<String, Object> var1);

    StAllDetailsFactor getWaterQualityStatis(Map<String, Object> var1);

    StAllDetailsFactor getDetail(Map<String, Object> var1);

    int getTableExist(String var1);

    int getDaySzCount(Map<String, Object> var1);

    List<StAllDetailsFactor> getDaySzList(Map<String, Object> var1);

    int getFactorStatistCount(Map<String, Object> var1);

    List<StAllDetailsFactor> getFactorStatistList(Map<String, Object> var1);

    Double getMaxPj(Map<String, Object> var1);

    int getAlmanacReportCount(Map<String, Object> var1);

    List<StAllDetailsFactor> getAlmanacReportList(Map<String, Object> var1);
}
