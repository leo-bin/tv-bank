package com.ccsu.jc.tvbank.dao;

import com.ccsu.jc.tvbank.domain.MessageEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDAO {

    /**
     * 直接保存用户留言信息到留言表
     */
    int saveMessage(MessageEntity message);


    /**
     * 根据ID查询这条帖子所有的回复
     */
    List<MessageEntity> messagelist(@Param("videoID") String videoID);


}
