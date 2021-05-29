package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.MessageDAO;
import com.ccsu.jc.tvbank.domain.MessageEntity;
import com.ccsu.jc.tvbank.domain.req.MessageReq;
import com.ccsu.jc.tvbank.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;


    @Override
    public boolean message(MessageReq messageReq) {
        MessageEntity message = messageReq.transferEntity();
        int bl = messageDAO.saveMessage(message);
        return bl > 0;
    }


    @Override
    public List<MessageEntity> messagelist(String videoID) {
        List<MessageEntity> list;
        list = messageDAO.messagelist(videoID);
        return list;
    }


}
