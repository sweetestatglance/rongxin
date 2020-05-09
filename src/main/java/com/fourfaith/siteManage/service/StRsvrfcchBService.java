//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.service;

import com.fourfaith.siteManage.model.StRsvrfcchB;
import java.util.List;
import java.util.Map;

public interface StRsvrfcchBService {
    int deleteByPrimaryKey(String var1);

    int insert(StRsvrfcchB var1);

    int insertSelective(StRsvrfcchB var1);

    StRsvrfcchB selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StRsvrfcchB var1);

    int updateByPrimaryKey(StRsvrfcchB var1);

    Integer getCount(Map<String, Object> var1);

    List<StRsvrfcchB> getList(Map<String, Object> var1);

    StRsvrfcchB findById(String var1);

    Integer add(StRsvrfcchB var1);

    Integer delete(String var1);

    Integer update(StRsvrfcchB var1);

    StRsvrfcchB findByStcd(String var1);
}
