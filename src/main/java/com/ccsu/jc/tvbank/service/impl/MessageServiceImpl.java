package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.MessageDAO;
import com.ccsu.jc.tvbank.domain.MessageEntity;
import com.ccsu.jc.tvbank.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public boolean message(MessageEntity message) {
        int bl = messageDAO.saveMessage(message);
        if (bl > 0) {
            return true;
        }
        return false;
    }


    @Override
    public List<MessageEntity> messagelist(String videoID) {
        List<MessageEntity> list;
        list = messageDAO.messagelist(videoID);
        return list;
    }


}
