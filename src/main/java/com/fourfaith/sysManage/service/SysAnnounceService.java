//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.SysAnnounce;
import com.fourfaith.utils.AjaxJson;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface SysAnnounceService {
    int deleteByPrimaryKey(String var1);

    int insert(SysAnnounce var1);

    int insertSelective(SysAnnounce var1);

    SysAnnounce selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysAnnounce var1);

    int updateByPrimaryKey(SysAnnounce var1);

    Integer getCount(Map<String, Object> var1);

    List<SysAnnounce> getList(Map<String, Object> var1);

    SysAnnounce findById(String var1);

    AjaxJson saveAnnounce(SysAnnounce var1, HttpServletRequest var2);

    Integer add(SysAnnounce var1);

    AjaxJson deleteAnnounce(String var1, HttpServletRequest var2);

    Integer delete(String var1);

    List<SysAnnounce> getListByRead(Map<String, Object> var1);

    int getCountByRead(Map<String, Object> var1);
}
