package com.zfs.web.config;

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


    /**
     * 项目中访问图片等静态资源的虚拟路径，映射保存有图片等资源的本地磁盘实际路径
     * handler为前端访问的虚拟路径，locations为存有资源的磁盘实际路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
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