package com.ccsu.jc.tvbank.api;

import com.ccsu.jc.tvbank.domain.UserEntity;
import com.ccsu.jc.tvbank.service.impl.LoginServiceImpl;
import com.ccsu.jc.tvbank.service.impl.MessageServiceImpl;
import com.ccsu.jc.tvbank.service.impl.RegisterServiceImpl;
import com.ccsu.jc.tvbank.service.impl.UserListServiceImpl;
import com.ccsu.jc.tvbank.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jc
 * @date 2021/5/2 16:04
 * @apiNote user api
 */
@Controller
public class UserController {

    @Autowired
    LoginServiceImpl loginServiceImpl;
    @Autowired
    UserListServiceImpl userListServiceImpl;
    @Autowired
    RegisterServiceImpl registerService;
    @Autowired
    MessageServiceImpl messageServiceImpl;


    @PostMapping("/login")
    public String web3(String userName, String passWord, HttpServletRequest request) {
        boolean bl = loginServiceImpl.loginuser(userName, passWord);
        if (bl) {
            //将用户的全部信息查询出来
            UserEntity list = userListServiceImpl.userlist(userName);
            //将用户ID放到session里面
            request.getSession().setAttribute("userID", list.getUserID());
            request.getSession().setAttribute("userName", userName);
            request.getSession().setAttribute("userHand", list.getUserHand());
            return "index";
        } else {
            //失败
            request.setAttribute("PHO", "用户名或密码错误!");
        }
        return "loginnew";
    }


    @PostMapping("/register")
    @ResponseBody
    public String web3(@RequestBody UserEntity user) {
        // 设置一个默认的用户ID UUID
        user.setUserID(GetUUID.getUUID());
        // 设置用户注册时 默认状态
        user.setUserState("正常");// 状态为0为正常用户
        // 设置注册用户的默认支付密码; (一开始忘记了)
        user.setUserPaypassword("123456");
        // 设置注册用户的默认头像
        user.setUserHand("/static/userHand_Top/upload/MyHand.png");

        boolean isOk = registerService.RegisterService(user);
        return isOk ? "1" : "0";
    }


}
