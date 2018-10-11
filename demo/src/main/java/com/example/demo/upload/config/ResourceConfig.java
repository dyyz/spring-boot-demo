package com.example.demo.upload.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

	@Autowired
	private Environment environment;
	
	@Override
    //需要告知系统，这是要被当成静态文件的！
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置文件上传的文件不拦截
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+ environment.getProperty("uplodapic.path"));
        //第一个方法设置访问路径前缀，第二个方法设置资源路径，为了能够加载js/css
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
