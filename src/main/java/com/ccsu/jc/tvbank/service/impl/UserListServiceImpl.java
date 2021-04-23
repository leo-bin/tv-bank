package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.impl.UserListDaoImpl;
import com.ccsu.jc.tvbank.domain.*;
import com.ccsu.jc.tvbank.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    UserListDaoImpl userListDaoImpl;

    public UserEntity userlist(String userName) {
        UserEntity user = userListDaoImpl.userlist(userName);
        return user;
    }

    public List<VideoEntity> videolist(String videocAtegory) {
        List<VideoEntity> list = userListDaoImpl.videolist(videocAtegory);
        return list;
    }

    public List<VideoEntity> videolistimit7() {
        List<VideoEntity> list = userListDaoImpl.videolistimit7();
        return list;
    }

    public List<VideoEntity> videolistimit5MAD() {
        List<VideoEntity> list = userListDaoImpl.videolistimit5MAD();
        return list;
    }


    public List<MessageEntity> messagelist(String videoID) {
        List<MessageEntity> list = null;
        list = userListDaoImpl.messagelist(videoID);
        return list;
    }

    public List<UserEntity> userlistpage(int pageInt) {
        List<UserEntity> list = null;
        list = userListDaoImpl.userlistpage(pageInt);
        return list;
    }

    public List<UserEntity> listmohu(String usermohu) {
        List<UserEntity> list = userListDaoImpl.listmohu(usermohu);
        return list;
    }

    public List<UserEntity> userPhone(String userPhone) {
        List<UserEntity> list = userListDaoImpl.userPhone(userPhone);
        return list;
    }

    public List<UserEntity> userID(String userID) {

        List<UserEntity> list = userListDaoImpl.userID(userID);
        return list;
    }

    public List<UserEntity> xiaoheiwu(String userStat) {
        List<UserEntity> user = userListDaoImpl.xiaoheiwu(userStat);
        return user;
    }

    public List<VideoEntity> Pagevideolist(String State, int dangqianye, int meiyexianshiduoshaoge) {
        List<VideoEntity> list = userListDaoImpl.Pagevideolist(State, dangqianye, meiyexianshiduoshaoge);
        return list;
    }

    public int videocoun(String countvideo) {
        int num = userListDaoImpl.videocoun(countvideo);
        return num;
    }

    public List<GridsEntity> gridslist() {
        List<GridsEntity> gridslist = userListDaoImpl.gridslist();
        return gridslist;
    }

    public GridsEntity gridsIDlist(String gridsID) {
        GridsEntity gridslist = userListDaoImpl.gridsIDlist(gridsID);
        return gridslist;
    }

    public List<UserEntity> userlistUserName(String userName) {
        List<UserEntity> user = userListDaoImpl.userlistUserName(userName);
        return user;
    }

    public List<ShoppingCart> shoppingcart(String userName) {
        List<ShoppingCart> user = userListDaoImpl.shoppingcart(userName);
        return user;
    }

    public List<VideoEntity> videolistimit6MAD() {
        List<VideoEntity> list = userListDaoImpl.videolistimit6MAD();
        return list;
    }

    public List<OrderTableEntity> ordertable(String userName) {
        List<OrderTableEntity> ordertable = userListDaoImpl.ordertable(userName);
        return ordertable;
    }

    public List<OrderTableEntity> ordertablelist() {
        List<OrderTableEntity> ordertable = userListDaoImpl.ordertablelist();
        return ordertable;
    }

    public List<OrderTableEntity> orderStat(String orderStat) {
        List<OrderTableEntity> ordertable = userListDaoImpl.orderStat(orderStat);
        return ordertable;
    }

    public int countordertable() {
        int count = userListDaoImpl.countordertable();
        return count;
    }

    public List<ForumEntity> forumEnt(String forumliebie) {
        List<ForumEntity> forumEnt = userListDaoImpl.forumEnt(forumliebie);
        return forumEnt;
    }

    public ForumEntity forumentitymmp(String forumID) {
        ForumEntity user = userListDaoImpl.forumentitymmp(forumID);
        return user;
    }

    public int counttable(String tableName) {
        int num = userListDaoImpl.counttable(tableName);
        return num;
    }

    public List<ForumReplyEntity> forumreply(String forumreplyID) {
        List<ForumReplyEntity> forumre = userListDaoImpl.forumreply(forumreplyID);
        return forumre;
    }


}
