package com.ccsu.jc.tvbank.service;

import com.ccsu.jc.tvbank.domain.MessageEntity;

import java.util.List;

public interface MessageService {

    /**
     * 直接保存用户留言信息到留言表
     */
    boolean message(MessageEntity message);

    /**
     * 根据ID查询出当前视频的所有留言
     */
    List<MessageEntity> messagelist(String videoID);


}
