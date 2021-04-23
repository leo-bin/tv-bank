package com.ccsu.jc.tvbank.mapper;


import com.ccsu.jc.tvbank.domain.MessageEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//留言表
public class RowMapperVideoEntity implements RowMapper<MessageEntity> {

	public MessageEntity mapRow(ResultSet arg0, int arg1) throws SQLException {
		MessageEntity message = new MessageEntity();
		message.setMessage(arg0.getString("message"));
		message.setMessageID(arg0.getString("messageID"));
		message.setMessageTime(arg0.getString("messageTime"));
		message.setMessageuserID(arg0.getString("messageuserID"));
		message.setMessageuserName(arg0.getString("messageuserName"));
		message.setMessagevideoID(arg0.getString("messagevideoID"));
		message.setMessageHand(arg0.getString("messageHand"));
		return message;
	}

}
