//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysNotice;
import java.util.List;
import java.util.Map;

public interface SysNoticeService {
    int deleteByPrimaryKey(String var1);

    int insert(SysNotice var1);

    int insertSelective(SysNotice var1);

    SysNotice selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysNotice var1);

    int updateByPrimaryKey(SysNotice var1);

    List<SysNotice> getList(Map<String, Object> var1);

    int deleteNotice(Integer var1);
}
