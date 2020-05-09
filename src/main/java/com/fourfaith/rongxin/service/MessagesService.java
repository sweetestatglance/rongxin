package com.fourfaith.rongxin.service;

import com.fourfaith.rongxin.model.MessageVo;
import com.fourfaith.rongxin.model.Openid;

import java.util.List;

public interface MessagesService {

    List<MessageVo> getvo(String tabname);

    List<Openid> getopenids();

    int addwxpushlog(MessageVo messageVo);

    Integer issended(MessageVo messageVo);
}
