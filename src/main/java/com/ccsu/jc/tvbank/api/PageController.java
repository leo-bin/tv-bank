package com.ccsu.jc.tvbank.api;

import com.ccsu.jc.tvbank.config.Auth;
import com.ccsu.jc.tvbank.domain.VideoEntity;
import com.ccsu.jc.tvbank.service.VideoService;
import com.ccsu.jc.tvbank.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jc
 * @date 2021/4/25 7:16 下午
 * Description：页面跳转器
 **/
@Controller
@RequestMapping("/tv-bank")
public class PageController {

    @Autowired
    private VideoService videoService;


    @Auth(validate = false)
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @Auth(validate = false)
    @RequestMapping("/zhuce")
    public String register() {
        return "zhuce";
    }


    // 将数据库里面所有的视频查询出来 发送到首页面
    @RequestMapping("/index")
    public ModelAndView index() {
        Map<String, Object> model = new HashMap<>(16);
        List<VideoEntity> list = videoService.videoList("1");
        List<VideoEntity> list2 = videoService.videoList("2");
        List<VideoEntity> list3 = videoService.videoList("3");

        model.put("list", list);
        model.put("list2", list2);
        model.put("list3", list3);

        // 随机查6条数据出来
        List<VideoEntity> video = videoService.videolistimit6MAD();
        model.put("video", video);
        return new ModelAndView("index", model);
    }
}
