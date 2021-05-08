package com.ccsu.jc.tvbank.service;

import com.ccsu.jc.tvbank.domain.ForumEntity;
import com.ccsu.jc.tvbank.domain.ForumReplyEntity;

import java.util.List;

/**
 * @author jc
 * @date 2021/5/8 18:15
 * @apiNote
 */
public interface ForumService {


    /**
     * 根据ID查询出此条论坛的全部信息
     */
    ForumEntity forumentitymmp(String forumID);

    /**
     * 根据ID查询出当前视频的所有留言
     */
    List<ForumReplyEntity> forumreply(String forumreplyID);


    /**
     * 直接保存论坛帖子
     */
    boolean forumfuck(ForumEntity forument);


    /**
     * 直接保存回复的帖子内容
     */
    boolean forumreply(ForumReplyEntity forumreply);

    /**
     * 根据论坛类型查询出符合条件的论坛
     */
    List<ForumEntity> forumEnt(String forumliebie);
}
