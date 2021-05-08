package com.ccsu.jc.tvbank.dao;


import com.ccsu.jc.tvbank.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {

    UserEntity select(@Param("userName") String userName);

    int insert(UserEntity user);


    /**
     * 将用户表里面所有的用户只查询出15条. 到时候方便分页
     */
    List<UserEntity> userlistpage(@Param("pageInt") int pageInt);

    /**
     * 根据用户输入的内容 进行模糊查询出所得
     */
    List<UserEntity> listmohu(@Param("usermohu") String usermohu);

    /**
     * 根据用户输入的手机号 进行模糊查询出所得
     */
    List<UserEntity> userPhone(@Param("userPhone") String userPhone);


    /**
     * 根据ID查询出用户的所有信息
     */
    List<UserEntity> userID(@Param("userID") String userID);


    /**
     * 修改用户的全部信息
     */
    int updateUser(UserEntity user);


}
