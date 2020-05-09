

package com.fourfaith.reportManage.service;

import com.fourfaith.reportManage.model.StFactorName;
import com.fourfaith.utils.FactorName;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface StFactorNameService {
    int deleteByPrimaryKey(String var1);

    int insert(StFactorName var1);

    int insertSelective(StFactorName var1);

    StFactorName selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(StFactorName var1);

    int updateByPrimaryKey(StFactorName var1);

    StFactorName getByEnterId(String var1);

    List<StFactorName> getList(Map<String, Object> var1);

    Map<String, FactorName> getFactorMap(String var1, List<String> var2, HttpServletRequest var3);

    Map<String, FactorName> getAllFactorMap(String var1, List<String> var2);
}
