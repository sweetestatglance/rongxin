package com.fourfaith.rongxin.dao;

import com.fourfaith.rongxin.model.MessageVo;
import com.fourfaith.rongxin.model.Openid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessagesMapper {

    List<MessageVo> getvo(@Param("tabname") String tabname);

    List<Openid> getopenids();

    int addwxpushlog(MessageVo messageVo);

    Integer issended(MessageVo messageVo);
}
