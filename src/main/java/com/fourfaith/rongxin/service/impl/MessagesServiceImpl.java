package com.fourfaith.rongxin.service.impl;

import com.fourfaith.rongxin.dao.MessagesMapper;
import com.fourfaith.rongxin.model.MessageVo;
import com.fourfaith.rongxin.model.Openid;
import com.fourfaith.rongxin.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messagesService")
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesMapper messagesMapper;


    @Override
    public List<MessageVo> getvo(String tabname) {
        return messagesMapper.getvo(tabname);
    }

    @Override
    public List<Openid> getopenids() {
        return messagesMapper.getopenids();
    }

    @Override
    public int addwxpushlog(MessageVo messageVo) {
        return messagesMapper.addwxpushlog(messageVo);
    }

    @Override
    public Integer issended(MessageVo messageVo) {
        return messagesMapper.issended(messageVo);
    }
}
