package com.ccsu.jc.tvbank.dao.entity;


import com.ccsu.jc.tvbank.domain.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


//用户表
public class RowMapperEntity implements RowMapper<UserEntity> {

	public UserEntity mapRow(ResultSet arg0, int arg1) throws SQLException {
		UserEntity user=new UserEntity();
		
		user.setPassWord(arg0.getString("passWord"));
		user.setUsersex(arg0.getString("usersex"));
		user.setUserAddress(arg0.getString("userAddress"));
		user.setUserCollection(arg0.getString("userCollection"));
		user.setUserEmial(arg0.getString("userEmial"));
		user.setUserHand(arg0.getString("userHand"));
		user.setUserID(arg0.getString("userID"));
		user.setUserIP(arg0.getString("userIP"));
		user.setUserLoginTime(arg0.getString("userLoginTime"));
		user.setUserMingzi(arg0.getString("userMingzi"));
		user.setUserName(arg0.getString("userName"));
		user.setUserPaypassword(arg0.getString("userPaypassword"));
		user.setUserPhone(arg0.getString("userPhone"));
		user.setUserQQ(arg0.getString("userQQ"));
		user.setUserState(arg0.getString("userState"));
		user.setUseryinghangka(arg0.getString("useryinghangka"));
		user.setUserRMB(arg0.getString("userRMB"));
		return user;
	}

	
	
}
