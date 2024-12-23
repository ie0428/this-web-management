package com.itheima.t.controller;

import com.itheima.t.pojo.Result;
import com.itheima.t.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    private final AliOSSUtils aliOSSUtils;
    @Autowired
    public UploadController(AliOSSUtils aliOSSUtils) {
        this.aliOSSUtils = aliOSSUtils;
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        //调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);
        //将图片上传完成后的url返回，用于浏览器回显展示
        return Result.success(url);
    }

}