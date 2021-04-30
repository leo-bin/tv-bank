package com.ccsu.jc.tvbank.api;

import com.ccsu.jc.tvbank.config.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jc
 * @date 2021/4/25 7:16 下午
 * Description：页面跳转器
 **/
@Controller
@RequestMapping("/page")
public class PageController {


    @GetMapping("/{page}")
    public String page1(@PathVariable String page) {
        return page;
    }

    @Auth(validate = false)
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
