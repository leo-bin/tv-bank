package com.ccsu.jc.tvbank.dao;

import com.ccsu.jc.tvbank.domain.ForumEntity;
import com.ccsu.jc.tvbank.domain.ForumReplyEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jc
 * @date 2021/5/8 16:37
 * @apiNote
 */
@Repository
public interface ForumDAO {

    /**
     * 根据论坛类型查询出符合条件的论坛
     */
    List<ForumEntity> forumEnt(@Param("forumliebie") String forumliebie);


    /**
     * 根据ID查询出此条论坛的全部信息
     */
    ForumEntity selectOne(@Param("forumID") String forumID);


    /**
     * 根据ID查询出当前视频的所有留言
     */
    List<ForumReplyEntity> forumReply(String forumReplyID);


    /**
     * 直接保存论坛帖子
     */
    int saveForum(ForumEntity forumEntity);

    /**
     * 直接保存回复的帖子内容
     */
    int saveForumReply(ForumReplyEntity forumReplyEntity);

}
