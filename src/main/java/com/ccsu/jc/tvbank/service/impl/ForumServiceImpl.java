package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.ForumDAO;
import com.ccsu.jc.tvbank.dao.MessageDAO;
import com.ccsu.jc.tvbank.domain.ForumEntity;
import com.ccsu.jc.tvbank.domain.ForumReplyEntity;
import com.ccsu.jc.tvbank.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jc
 * @date 2021/5/8 18:15
 * @apiNote
 */
@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumDAO forumDAO;

    @Override
    public boolean forumfuck(ForumEntity forument) {
        int bl = forumDAO.saveForum(forument);
        if (bl > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean forumreply(ForumReplyEntity forumreply) {
        int bl = forumDAO.saveForumReply(forumreply);
        if (bl > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ForumEntity> forumEnt(String forumliebie) {
        List<ForumEntity> forumEnt = forumDAO.forumEnt(forumliebie);
        return forumEnt;
    }

    @Override
    public ForumEntity forumentitymmp(String forumID) {
        ForumEntity user = forumDAO.selectOne(forumID);
        return user;
    }


    @Override
    public List<ForumReplyEntity> forumreply(String forumreplyID) {
        List<ForumReplyEntity> forumreply = forumDAO.forumReply(forumreplyID);
        return forumreply;
    }

}
