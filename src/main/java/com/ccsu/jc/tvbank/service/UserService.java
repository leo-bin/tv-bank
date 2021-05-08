package com.ccsu.jc.tvbank.service;


import com.ccsu.jc.tvbank.domain.*;

import java.util.List;

public interface UserService {

    /**
     * 根据用户的名字 查询出用户的所有信息
     */
    boolean  login(String userName,String passWord);

    boolean register(UserEntity userEntity);

    UserEntity userInfo(String userName);


    List<UserEntity> userList(int page);


    /**
     * 根据用户输入的内容 进行模糊查询出所得
     */
    List<UserEntity> listmohu(String usermohu);


    /**
     * 根据用户输入的手机号 进行模糊查询出所得
     */
    List<UserEntity> userPhone(String userPhone);


    /**
     * 根据ID查询出用户的所有信息
     */
    List<UserEntity> userID(String userID);

    boolean updateLoginPassword(String userName, String newpassWord);

    /**
     * 修改邮箱
     */
    boolean updateLoginEmail(String userName, String newEmail);

    /**
     * 修改手机号码
     */
    boolean updateLoginPhone(String userName, String newuserPhone);


    /**
     * 修改头像地址
     */
    boolean updateLoginHand(String userName, String newuserHand);


    /**
     * 修改用户的全部信息
     */
    boolean updateUser(UserEntity user);


}
