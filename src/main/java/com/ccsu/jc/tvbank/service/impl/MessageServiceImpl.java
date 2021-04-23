package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.impl.MessageImpl;
import com.ccsu.jc.tvbank.domain.ForumEntity;
import com.ccsu.jc.tvbank.domain.ForumReplyEntity;
import com.ccsu.jc.tvbank.domain.MessageEntity;
import com.ccsu.jc.tvbank.domain.ShoppingCart;
import com.ccsu.jc.tvbank.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageImpl messageImpl;

    public boolean message(MessageEntity message) {
        int bl = messageImpl.message(message);
        if (bl > 0) {
            return true;
        }
        return false;
    }

    public boolean Shoppingcart(ShoppingCart shoppingCart) {
        int bl = messageImpl.Shoppingcart(shoppingCart);
        if (bl > 0) {
            return true;
        }
        return false;
    }

    public boolean forumfuck(ForumEntity forument) {
        int bl = messageImpl.forumfuck(forument);
        if (bl > 0) {
            return true;
        }
        return false;
    }

    public boolean forumreply(ForumReplyEntity forumreply) {
        int bl = messageImpl.forumreply(forumreply);
        if (bl > 0) {
            return true;
        }
        return false;
    }

}
