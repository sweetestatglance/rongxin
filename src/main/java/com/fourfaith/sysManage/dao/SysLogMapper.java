//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysLog;
import java.util.List;
import java.util.Map;

public interface SysLogMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysLog var1);

    int insertSelective(SysLog var1);

    SysLog selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysLog var1);

    int updateByPrimaryKey(SysLog var1);

    Integer getCount(Map<String, Object> var1);

    List<SysLog> getList(Map<String, Object> var1);

    SysLog findById(String var1);
}
