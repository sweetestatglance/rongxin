//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.dao;

import com.fourfaith.sysManage.model.SysUserAnnounce;
import java.util.List;
import java.util.Map;

public interface SysUserAnnounceMapper {
    int deleteByPrimaryKey(String var1);

    int insert(SysUserAnnounce var1);

    int insertSelective(SysUserAnnounce var1);

    SysUserAnnounce selectByPrimaryKey(String var1);

    int updateByPrimaryKeySelective(SysUserAnnounce var1);

    int updateByPrimaryKey(SysUserAnnounce var1);

    Integer getCount(Map<String, Object> var1);

    List<SysUserAnnounce> getList(Map<String, Object> var1);

    SysUserAnnounce findById(String var1);

    int deleteByNoticeId(String var1);

    SysUserAnnounce findByUserIdAndAnnId(Map<String, Object> var1);
}
