package com.ccsu.jc.tvbank.api;

import com.ccsu.jc.tvbank.service.VideoService;
import com.ccsu.jc.tvbank.task.TestMain;
import com.ccsu.jc.tvbank.domain.*;
import com.ccsu.jc.tvbank.service.MessageService;
import com.ccsu.jc.tvbank.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/tv-bank")
public class VideoController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private VideoService videoService;


    @PostConstruct
    public void init() {
        System.out.println("我只会被调用一次");
        TestMain sf = new TestMain();
        sf.main(null);
    }


    @RequestMapping("/video")
    public String video(@RequestParam("dizhi") String dizhi,
                        @RequestParam("shipingID") String shipingID, Model model) {
        // 根据视频ID查询出 此视频的所有留言
        List<MessageEntity> messagelist = messageService.messagelist(shipingID);
        System.out.println("dizhi=" + dizhi);
        model.addAttribute("messagelist", messagelist);
        model.addAttribute("dizhi", dizhi);
        model.addAttribute("shipingID", shipingID);

        return "video";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "keyWord") String keyWord) {
        Map<String, Object> map = new HashMap<>(16);
        List<VideoEntity> videos = videoService.searchByName(keyWord);
        map.put("videos", videos);
        return new ModelAndView("searchList", map);
    }


    @GetMapping("/getByCategory")
    public ModelAndView getByCategory(@RequestParam("category") String category) {
        Map<String, Object> map = new HashMap<>(16);
        List<VideoEntity> videos = videoService.videoList(category);
        map.put("videos", videos);
        return new ModelAndView("searchList", map);
    }


    // AJAX 提交
    @RequestMapping(value = "ajaxTuiJian", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public void ajaxTuiJian(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<VideoEntity> list = videoService.videolistimit7();
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
        List<VideoEntity> list = videoService.videolistimit5MAD();
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String sbb = gson.toJson(list);
        out.write(sbb);
    }


    // 将数据库里面所有的视频查询出来 发送到首页面
    @RequestMapping("testshabi.sf")
    public String testshabi(HttpServletRequest request) {
        // request.setAttribute("test", "测试");
        int tag1 = videoService.videoCount("1");
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
        List<VideoEntity> list = videoService.pageVideoList(State, dangqianye2, meiyexianshiduoshaoge);

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
    public String videoFileTopView() {
        return "videoFileTop";
    }


    @RequestMapping("/upLoadFile")
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
