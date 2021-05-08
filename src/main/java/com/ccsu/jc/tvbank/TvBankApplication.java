package com.ccsu.jc.tvbank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 视频素材网站
 */
@MapperScan(basePackages = "com.ccsu.jc.tvbank.dao")
@SpringBootApplication
public class TvBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(TvBankApplication.class, args);
    }

}
