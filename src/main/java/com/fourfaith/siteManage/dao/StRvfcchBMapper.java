//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.dao;

import com.fourfaith.siteManage.model.StRvfcchB;
import java.util.List;
import java.util.Map;

public interface StRvfcchBMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StRvfcchB var1);

    int insertSelective(StRvfcchB var1);

    StRvfcchB selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StRvfcchB var1);

    int updateByPrimaryKey(StRvfcchB var1);

    List<StRvfcchB> getList(Map<String, Object> var1);

    StRvfcchB findByStcd(String var1);
}
