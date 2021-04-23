package com.ccsu.jc.tvbank.dao.impl;

import com.ccsu.jc.tvbank.dao.DataJdbcTemplate;
import com.ccsu.jc.tvbank.dao.MessageDao;
import com.ccsu.jc.tvbank.domain.ForumEntity;
import com.ccsu.jc.tvbank.domain.ForumReplyEntity;
import com.ccsu.jc.tvbank.domain.MessageEntity;
import com.ccsu.jc.tvbank.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageImpl implements MessageDao {
	
	@Autowired
	DataJdbcTemplate jdbcTemplate;//得到模板
	
	
	public int message(MessageEntity message) {
		//String chaxunSql="insert into user(userID,userName,passWord,userPhone,userState,userEmial,userHand,userPaypassword) values(?,?,?,?,?,?,?,?)";
		
		String sql="insert into message(messageID,messagevideoID,"
				+ "messageuserID,messageuserName,message,"
				+ "messageTime,messageHand) values(?,?,?,?,?,?,?)";
		int num = jdbcTemplate.getJdbcTemplate().update(sql, new Object[]{message.getMessageID(),
					message.getMessagevideoID(),message.getMessageuserID()
					,message.getMessageuserName(),
					message.getMessage(),message.getMessageTime(),message.getMessageHand()});
		
		
		return num;
	}


	public int Shoppingcart(ShoppingCart shoppingCart) {
		String sql = "insert into shoppingcart(cartID,userName,shoopingID,shoopingName,shoopingImg,shoopingjiage) values (?,?,?,?,?,?)";
		int num = jdbcTemplate.getJdbcTemplate().update(sql,new Object[]{shoppingCart.getCartID(),shoppingCart.getUserName(),shoppingCart.getShoopingID(),shoppingCart.getShoopingName(),shoppingCart.getShoopingImg(),shoppingCart.getShoopingjiage()});
		return num;
	}


	public int forumfuck(ForumEntity forument) {
		String sql = "insert into forum(forumID,forumBT,forummessage,forumuserName,forumTime,forumliebie,forumAmount,firumhand) values (?,?,?,?,?,?,?,?)";
		int num = jdbcTemplate.getJdbcTemplate().update(sql,new Object[]{forument.getForumID(),forument.getForumBT(),forument.getForummessage(),forument.getForumuserName(),forument.getForumTime(),forument.getForumliebie(),forument.getForumAmount(),forument.getFirumhand()});
		return num;
	}


	public int forumreply(ForumReplyEntity forumreply) {/*
		public String replyid;//回复ID
		public String replyneirong;//回帖内容
		public String replytime;//回帖时间
		public String replyhand;//回帖人头像
		public String replytieziid;//回复帖子的ID
		*/
		String sql = "insert into forumreply (replyid,replyneirong,replytime,replyhand,replytieziid,replyname) values (?,?,?,?,?,?)";
		
		int num = jdbcTemplate.getJdbcTemplate().update(sql,new Object[]{forumreply.getReplyid(),forumreply.getReplyneirong(),forumreply.getReplytime(),forumreply.getReplyhand(),forumreply.getReplytieziid(),forumreply.getReplyname()});
		return num;
	}




}
