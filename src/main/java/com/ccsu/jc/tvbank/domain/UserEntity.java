package com.ccsu.jc.tvbank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    private String userID;//用户ID
    private String userMingzi;//用户真实名字
    private String userName;//用户名
    private String passWord;//密码
    private String usersex;//性别
    private String userHand;//头像
    private String userPhone;//手机
    private String userQQ;//qq
    private String userEmail;//邮箱
    private String userCollection;//收藏
    private String userState;//状态
    private String userLoginTime;//最后登录时间
    private String userIP;//登录IP地址

}
