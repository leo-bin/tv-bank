package com.ccsu.jc.tvbank.config;

import com.ccsu.jc.tvbank.TvBankApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author binLi
 * @date 2021/4/25 4:38 下午
 * Description：
 **/
@Configuration
@ComponentScan(basePackageClasses = TvBankApplication.class)
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 配置web静态资源位置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


}
