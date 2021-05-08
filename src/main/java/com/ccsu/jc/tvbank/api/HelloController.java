package com.ccsu.jc.tvbank.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jc
 * @date 2021/4/23 5:35 下午
 * Description：
 **/
@Slf4j
@Controller
public class HelloController {

    @GetMapping("/tv_bank/hello")
    public String hello() {
        return "hello there～";
    }

}
