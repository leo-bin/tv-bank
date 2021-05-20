package com.ccsu.jc.tvbank.api;

import com.alibaba.fastjson.JSONObject;
import com.ccsu.jc.tvbank.domain.UserEntity;
import com.ccsu.jc.tvbank.service.impl.UserServiceImpl;
import com.ccsu.jc.tvbank.utils.GetUUID;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jc
 * @date 2021/5/2 16:04
 * @apiNote user api
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("passWord") String passWord,
                        HttpServletRequest request) {
        JSONObject returnJson = new JSONObject();
        boolean bl = userService.login(userName, passWord);
        if (bl) {
            //将用户的全部信息查询出来
            UserEntity list = userService.userInfo(userName);
            //将用户ID放到session里面
            returnJson.put("code", 1);
            returnJson.put("userName", userName);
            returnJson.put("userHand", list.getUserHand());
        } else {
            //失败
            returnJson.put("code", 0);
            returnJson.put("msg", "用户名或密码错误!");
        }
        return returnJson.toJSONString();
    }


    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody UserEntity user) {
        // 设置一个默认的用户ID UUID
        user.setUserID(GetUUID.getUUID());
        // 设置用户注册时 默认状态 状态为0为正常用户
        user.setUserState("正常");
        // 设置注册用户的默认头像
        user.setUserHand("/static/userHand_Top/upload/MyHand.png");
        boolean isOk = userService.register(user);
        return isOk ? "1" : "0";
    }


    // 信息修改界面
    @RequestMapping("User_full_information.sf")
    public ModelAndView User_full_information(HttpServletRequest request) {
        // 得到登录用户的名字
        String userName = (String) request.getSession().getAttribute("userName");
        UserEntity user = userService.userInfo(userName);
        Map<String, Object> model = new HashMap<>(16);
        model.put("user", user);
        return new ModelAndView("User_full_information", model);
    }

    // 修改登录密码界面
    @RequestMapping("Update_login_password.sf")
    public String updateLoginPassword(String passWord, String newpassWord,
                                      String newpassWord2,
                                      HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (passWord != null || newpassWord != null || newpassWord2 != null) {
            if (newpassWord.equals(newpassWord2)) {
                boolean bl = userService.updateLoginPassword(userName, newpassWord);
                if (bl) {
                    return "User_full_information";
                } else {
                    request.setAttribute("PassWordErro", "对不起,旧密码输入有误!");
                }
            } else {
                request.setAttribute("PassWordErro", "两次密码输入有误!");
            }
        }
        return "updateLoginPassword";
    }

    // 修改邮箱界面
    @RequestMapping("Update_email.sf")
    public String updateEmail(HttpServletRequest request, String emial, String newemial) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || emial != null || newemial != null) {
            boolean bl = userService.updateLoginEmail(userName, newemial);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro2", "对不起,原邮箱输入错误!");
            }
        } else {
            request.setAttribute("PassWordErro", "输入不能为空!");
        }
        return "updateEmail";
    }

    // 修改手机界面
    @RequestMapping("Update_Phone.sf")
    public String updatePhone(HttpServletRequest request, String userPhone, String newuserPhone) {
        // 得到session里面的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || userPhone != null || newuserPhone != null) {
            boolean bl = userService.updateLoginPhone(userName, newuserPhone);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro3", "对不起,原手机号码错误!");
            }
        } else {
            request.setAttribute("PassWordErro3", "输入不能为空!");
        }
        return "updatePhone";
    }


    // 用户查看所有信息界面
    @RequestMapping("Information.sf")
    public ModelAndView Information(HttpServletRequest request) {
        // 得到登录用户的名字
        String userName = (String) request.getSession().getAttribute("userName");
        UserEntity user = userService.userInfo(userName);
        Map<String, Object> model = new HashMap<>(16);
        model.put("user", user);
        return new ModelAndView("Information", model);
    }

    @RequestMapping("userHand.sf")
    public String userHand(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        String path2 = (String) request.getSession().getAttribute("fuckyou");
        UserEntity user = userService.userInfo(userName);
        String newuserHand = path2;
        boolean bl = userService.updateLoginHand(userName, newuserHand);
        if (bl) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        return "User_full_information";
    }


    // AJAX 提交 根据用户名查询
    @RequestMapping(value = "userchaxunmessage1", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void userchaxunmessage1(HttpServletRequest request, HttpServletResponse response,
                                   String message) throws IOException {
        String shuaige = message;
        // 得到内容 ajax提交进来
        List<UserEntity> list = userService.listmohu("%" + shuaige + "%");
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        if (list.size() == 0) {
            sbb = "0";
        }
        out.write(sbb);
    }


    // AJAX 提交 根据用户名查询
    @RequestMapping(value = "userchaxunmessage2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    void userchaxunmessage2(HttpServletRequest request, HttpServletResponse response,
                            String message) throws IOException {
        String shuaige = message;
        // 得到内容 ajax提交进来
        List<UserEntity> list = userService.userPhone("%" + shuaige + "%");
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        if (list.size() == 0) {
            sbb = "0";
        }
        out.write(sbb);
    }

    // AJAX 提交 根据ID查询
    @RequestMapping(value = "userID.sf", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void userID(HttpServletRequest request, HttpServletResponse response, String userID)
            throws IOException {
        // 得到内容 ajax提交进来
        List<UserEntity> list = userService.userID(userID);
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        if (list.size() == 0) {
            sbb = "0";
        }
        out.write(sbb);
    }

    // AJAX 提交 修改用户数据
    @RequestMapping("updateuser.sf")
    public String updateuser(String userID, String userName, String userzhenshiName, String userSex, String passWord,
                             String addr, String userPhone, String userQQ, String userEmial, HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        String echo;
        // 得到内容 ajax提交进来
        UserEntity user = new UserEntity();
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        user.setUserName(userName);
        user.setUserMingzi(userzhenshiName);
        user.setUsersex(userSex);
        user.setPassWord(passWord);
        user.setUserPhone(userPhone);
        user.setUserQQ(userQQ);
        user.setUserEmail(userEmial);
        user.setUserID(userID);
        boolean bl = userService.updateUser(user);
        if (bl) {
            echo = "修改成功";
            System.out.println("成功");
        } else {
            echo = "修改失败,请联系管理员";
        }
        request.setAttribute("echo", echo);
        return "forward:/Houtai.sf";
    }


}
