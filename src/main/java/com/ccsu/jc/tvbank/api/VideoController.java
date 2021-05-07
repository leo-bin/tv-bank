package com.ccsu.jc.tvbank.api;

import com.ccsu.jc.tvbank.task.TestMain;
import com.ccsu.jc.tvbank.domain.*;
import com.ccsu.jc.tvbank.service.MessageService;
import com.ccsu.jc.tvbank.service.UserListService;
import com.ccsu.jc.tvbank.service.impl.AddServiceImpl;
import com.ccsu.jc.tvbank.service.impl.MessageServiceImpl;
import com.ccsu.jc.tvbank.service.impl.UpdateLoginPasswordServiceImpl;
import com.ccsu.jc.tvbank.service.impl.UserListServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VideoController {

    @Autowired
    AddServiceImpl addserviceimpl;
    @Autowired
    UserListService userListService;
    @Autowired
    MessageServiceImpl messageServiceImpl;
    @Autowired
    MessageService messageService;
    @Autowired
    UpdateLoginPasswordServiceImpl updateLoginPasswordService;
    @Autowired
    UserListServiceImpl userListServiceImpl;

    @PostConstruct
    public void init() {
        System.out.println("我只会被调用一次");
        TestMain sf = new TestMain();
        sf.main(null);
    }


    @RequestMapping("/video")
    public ModelAndView video(String dizhi, HttpServletRequest request, String shipingID) {
        // 获得地址
        request.setAttribute("dizhi", dizhi);
        Map model = new HashMap();
        request.setAttribute("shipingID", shipingID);
        // 根据视频ID查询出 此视频的所有留言
        List<MessageEntity> messagelist = userListService.messagelist(shipingID);
        for (MessageEntity message : messagelist) {
            // 得到用户名
            message.getMessageuserName();
            // 根据每个用户名查询出每个用户对应的头像地址
            model.put("messagelist", messagelist);
        }
        return new ModelAndView("video", model);
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "keyWord") String keyWord) {
        Map<String, Object> map = new HashMap<>(16);
        // TODO: BY leo-bin 2021/5/7
        // TODO-LIST: 模糊匹配sql（like） 查出结果封装好返回前端


        return new ModelAndView("searchList", map);
    }



    // AJAX 提交
    @RequestMapping(value = "ajaxTuiJian", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public void ajaxTuiJian(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<VideoEntity> list = userListServiceImpl.videolistimit7();
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        out.write(sbb);
    }

    // AJAX 提交
    @RequestMapping(value = "ajaxTuiJian2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void webajax2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<VideoEntity> list = userListServiceImpl.videolistimit5MAD();
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        out.write(sbb);
    }


    // 信息修改界面
    @RequestMapping("User_full_information.sf")
    public ModelAndView User_full_information(HttpServletRequest request) {
        // 得到登录用户的名字
        String userName = (String) request.getSession().getAttribute("userName");
        UserEntity user = userListServiceImpl.userlist(userName);
        Map model = new HashMap();

        model.put("user", user);

        return new ModelAndView("User_full_information", model);
    }

    // 修改登录密码界面
    @RequestMapping("Update_login_password.sf")
    public String Update_login_password(String passWord, String newpassWord,
                                        String newpassWord2,
                                        HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (passWord != null || newpassWord != null || newpassWord2 != null) {
            if (newpassWord.equals(newpassWord2)) {
                boolean bl = updateLoginPasswordService.Update_login_password(userName, passWord, newpassWord);
                if (bl) {
                    return "User_full_information";
                } else {
                    request.setAttribute("PassWordErro", "对不起,旧密码输入有误!");
                }
            } else {
                request.setAttribute("PassWordErro", "两次密码输入有误!");
            }
        }
        return "Update_login_password";
    }

    // 修改邮箱界面
    @RequestMapping("Update_email.sf")
    public String Update_email(HttpServletRequest request, String emial, String newemial) {
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || emial != null || newemial != null) {
            boolean bl = updateLoginPasswordService.Update_login_Emial(userName, emial, newemial);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro2", "对不起,原邮箱输入错误!");
            }
        } else {
            request.setAttribute("PassWordErro", "输入不能为空!");
        }
        return "Update_email";
    }

    // 修改手机界面
    @RequestMapping("Update_Phone.sf")
    public String Update_Phone(HttpServletRequest request, String userPhone, String newuserPhone) {
        // 得到session里面的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        if (userName != null || userPhone != null || newuserPhone != null) {
            boolean bl = updateLoginPasswordService.Update_login_Phone(userName, userPhone, newuserPhone);
            if (bl) {
                return "User_full_information";
            } else {
                request.setAttribute("PassWordErro3", "对不起,原手机号码错误!");
            }
        } else {
            request.setAttribute("PassWordErro3", "输入不能为空!");
        }
        return "Update_Phone";
    }


    // 用户查看所有信息界面
    @RequestMapping("Information.sf")
    public ModelAndView Information(HttpServletRequest request) {
        // 得到登录用户的名字
        String userName = (String) request.getSession().getAttribute("userName");
        UserEntity user = userListServiceImpl.userlist(userName);
        Map model = new HashMap();
        model.put("user", user);
        return new ModelAndView("Information", model);
    }

    @RequestMapping("userHand.sf")
    public String userHand(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        String path2 = (String) request.getSession().getAttribute("fuckyou");
        UserEntity user = userListServiceImpl.userlist(userName);
        String userHand = user.getUserHand();
        String newuserHand = path2;
        boolean bl = updateLoginPasswordService.Update_login_hand(userName, userHand, newuserHand);
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
        List<UserEntity> list = userListServiceImpl.listmohu("%" + shuaige + "%");
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
        List<UserEntity> list = userListServiceImpl.userPhone("%" + shuaige + "%");
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
        List<UserEntity> list = userListServiceImpl.userID(userID);
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
        user.setUserAddress(addr);
        user.setUserPhone(userPhone);
        user.setUserQQ(userQQ);
        user.setUserEmial(userEmial);
        user.setUserID(userID);
        boolean bl = updateLoginPasswordService.Update_user(user);
        if (bl) {
            echo = "修改成功";
            System.out.println("成功");
        } else {
            echo = "修改失败,请联系管理员";
        }
        request.setAttribute("echo", echo);
        return "forward:/Houtai.sf";
    }


    // 将数据库里面所有的视频查询出来 发送到首页面
    @RequestMapping("testshabi.sf")
    public String testshabi(HttpServletRequest request) {
        // request.setAttribute("test", "测试");
        int tag1 = userListServiceImpl.videocoun("1");
        // System.out.println("视频一共有"+tag1);
        request.setAttribute("tag1", tag1);

        int tag4 = 12;
        // 每页显示多少
        request.setAttribute("tag4", tag4);
        return "DisplayVideo";
    }

    // AJAX 提交
    @RequestMapping(value = "ajaxtijiao1.sf", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void ajaxtijiao(HttpServletRequest request, HttpServletResponse response, String State,
                           String dangqianye, int meiyexianshiduoshaoge) throws IOException {
        int dangqianye2 = Integer.parseInt(dangqianye);
        // State
        List<VideoEntity> list = userListServiceImpl.Pagevideolist(State, dangqianye2, meiyexianshiduoshaoge);

        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        out.write(sbb);
    }


    /************************ 文件上传 ****************************************/


    @RequestMapping("/videoFileTop")
    public String videoFileTop(@RequestParam("files") MultipartFile[] files,
                               HttpServletRequest request, String biaoti,
                               String Fruit) {
        System.out.println("用户输入的标题为:" + biaoti + Fruit);
        if (files != null && files.length > 0) {
            // 循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                System.out.println("正在调用保存方法");
                // saveFile(file);
                // 判断文件是否为空
                if (!file.isEmpty()) {
                    try {
                        // new 出一个实体
                        VideoTopEntity video = new VideoTopEntity();
                        // 放入session中
                        request.getSession().setAttribute("video", video);// 放入到session中

                        System.out.println("文件总大小" + file.getSize());
                        // 文件保存路径
                        String filePath = "C:/videotest/" + file.getOriginalFilename();
                        System.out.println(filePath);
                        /******************** 测试 **************************/
                        File storeFile = new File(filePath);
                        // 得到输入流
                        InputStream in = file.getInputStream();
                        // 得到文件的输出流
                        OutputStream out = new FileOutputStream(storeFile);
                        // 文件总大小
                        long max = file.getSize();
                        video.setFileSize(max);
                        video.setFilename(file.getOriginalFilename());
                        // 剩余大小
                        long other = max;
                        int len = 0;// 读取写入长度
                        // 读写缓冲
                        byte[] b = new byte[300];
                        // 循环从输入流写入到输出流,结束循环是len==-1
                        while ((len = in.read(b)) != -1) {
                            out.write(b, 0, len);
                            other -= len;
                            video.setFileSY(other);
                            // System.out.println("剩余大小:"+other);
                            // 给DTO设置other
                            // dto.setOther(other);
                            // System.out.println("总大小="+max+"剩余大小="+other);
                            // z总 max
                            // 剩余 other
                            // 传了 max-other
                            float zong = (float) (Integer.parseInt(String.valueOf(max)));
                            int shengxia = Integer.parseInt(String.valueOf(other));
                            float ii3 = (float) zong - shengxia;// 传了多少
                            if (shengxia != 0) {
                                int baifenbi = (int) ((ii3 / zong) * 100);
                                video.setBaifenbi(baifenbi);
                            }
                        }
                        out.flush();// 刷新
                        out.close();// 关闭
                        in.close();// 关闭
                        video.setTag(1);// 标记为1的时候表示上传成功
                        System.out.println("上传成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("上传结束");
        return "videoFileTop";
    }


    /************************* 这里写个百分比的AJAX *******************************/
    @RequestMapping(value = "baifenbiAJAX", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void baifenbiAJAX(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getSession().getAttribute("video") == null) {
            // new 出一个实体
            VideoTopEntity video = new VideoTopEntity();
            video.setBaifenbi(0);
            video.setFilename("请稍后..");
            video.setFileSize(0);
            video.setFileSY(0);
            video.setTag(0);
            // 放入session中
            request.getSession().setAttribute("video", video);// 放入到session中
        } else {
            VideoTopEntity video = (VideoTopEntity) request.getSession().getAttribute("video");
            // 设置编码
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String sbb = gson.toJson(video);
            out.write(sbb);
        }
    }

}
