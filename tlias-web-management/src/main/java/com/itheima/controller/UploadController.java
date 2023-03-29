package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    // 本地存储文件的方式
    /*@PostMapping(/upload)
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传: {}, {}, {}", username, age, image);
        // 获取原始文件名
        String originalFilename = image.getOriginalFilename();
        // 构造唯一文件名 -uuid(通用唯一识别码)
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名: {}", newFileName);

        // 将接收到的文件存储在服务器的磁盘目录中 C:\Users\admin\Desktop\image
        image.transferTo(new File("C:\\Users\\admin\\Desktop\\image\\"+originalFilename));
        return Result.success();
    }*/


    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传: {}", image.getOriginalFilename());

        // 调用阿里云工具类上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问的url: {}", url);
        return Result.success(url);
    }
}
