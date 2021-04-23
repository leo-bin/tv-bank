package com.ccsu.jc.tvbank.dao;


import com.ccsu.jc.tvbank.domain.UserEntity;

public interface RegisterDao {
	
	/**
	 * 注册接口
	 * 
	 * 先根据用户名查询出是否有此用户
	 * 如果没有 即可注册
	 * @return
	 */
	public int Register(UserEntity user );
}
