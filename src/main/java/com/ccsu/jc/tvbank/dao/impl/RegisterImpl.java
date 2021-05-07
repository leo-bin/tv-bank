package com.ccsu.jc.tvbank.dao.impl;

import com.ccsu.jc.tvbank.dao.DataJdbcTemplate;
import com.ccsu.jc.tvbank.dao.RegisterDao;
import com.ccsu.jc.tvbank.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterImpl implements RegisterDao {

    @Autowired
    DataJdbcTemplate jdbcTemplate;


    public int Register(UserEntity user) {
        //默认为0
        int register = 0;
        //SELECT count(*) FROM user WHERE userName="admin" and `passWord`="123321aaa";
        //先根据用户名查询
        String sql = "SELECT count(*) FROM user WHERE userName = ?";
        int num = jdbcTemplate.getJdbcTemplate().queryForObject(sql, new Object[]{user.getUserName()}, Integer.class);
        if (num > 0) {
            System.out.println("此用户被注册");
        } else {
            //开始注册
            String chaxunSql = "insert into user(userID,userName,passWord,userPhone,userState,userEmial,userHand,userPaypassword) values(?,?,?,?,?,?,?,?)";
            register = jdbcTemplate.getJdbcTemplate().update(chaxunSql, new Object[]{user.getUserID(), user.getUserName(), user.getPassWord(), user.getUserPhone(), user.getUserState(), user.getUserEmial(), user.getUserHand(), user.getUserPaypassword()});

        }
        return register;
    }

}
