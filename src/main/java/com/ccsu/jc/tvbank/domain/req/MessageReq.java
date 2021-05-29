package com.ccsu.jc.tvbank.domain.req;

import com.ccsu.jc.tvbank.domain.MessageEntity;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @author jc
 * @date 2021/5/26 21:00
 * Description:
 */
@Data
public class MessageReq {

    private String content;
    private String userId;
    private String name;
    private String userHand;
    private String videoId;


    public MessageEntity transferEntity() {
        MessageEntity entity = new MessageEntity();
        entity.setMessageID(UUID.randomUUID().toString());
        entity.setMessage(this.content);
        entity.setMessageuserName(this.name);
        entity.setMessageHand(this.userHand);
        entity.setMessageuserID(this.userId);
        entity.setMessagevideoID(this.videoId);
        entity.setMessageTime(new Date().toString());
        return entity;
    }
}
