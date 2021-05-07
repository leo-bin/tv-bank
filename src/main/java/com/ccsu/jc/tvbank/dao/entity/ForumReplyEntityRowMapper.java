package com.ccsu.jc.tvbank.dao.entity;


import com.ccsu.jc.tvbank.domain.ForumReplyEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForumReplyEntityRowMapper implements RowMapper<ForumReplyEntity> {

	public ForumReplyEntity mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		ForumReplyEntity u = new ForumReplyEntity();
		u.setReplyhand(arg0.getString("replyhand"));
		u.setReplyid(arg0.getString("replyid"));
		u.setReplyneirong(arg0.getString("replyneirong"));
		u.setReplytieziid(arg0.getString("replytieziid"));
		u.setReplytime(arg0.getString("replytime"));
		u.setReplyname(arg0.getString("replyname"));
		return u;
	}

}
