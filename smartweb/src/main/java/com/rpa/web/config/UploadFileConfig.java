package com.rpa.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * @author: xiahui
 * @date: Created in 2019/10/10 19:58
 * @description: 上传文件配置
 * @version: 1.0
 */
@Configuration
public class UploadFileConfig implements WebMvcConfigurer {


    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径

        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        //文件最大
        factory.setMaxFileSize(DataSize.ofGigabytes(10));
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofGigabytes(1000));
        return factory.createMultipartConfig();
    }

}