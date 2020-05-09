//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.dao;

import com.fourfaith.reportManage.model.StFactorName;
import java.util.List;
import java.util.Map;

public interface StFactorNameMapper {
    int deleteByPrimaryKey(String var1);

    int insert(StFactorName var1);

    int insertSelective(StFactorName var1);

    StFactorName selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StFactorName var1);

    int updateByPrimaryKey(StFactorName var1);

    List<StFactorName> getList(Map<String, Object> var1);

    StFactorName getByEnterId(String var1);
}
