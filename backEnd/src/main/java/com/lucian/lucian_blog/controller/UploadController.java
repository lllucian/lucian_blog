package com.lucian.lucian_blog.controller;

import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.manager.UploadFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin/upload_file")
public class UploadController {
    UploadFileManager uploadFileManager;

    @Autowired
    public void setUploadFileManager(UploadFileManager uploadFileManager) {
        this.uploadFileManager = uploadFileManager;
    }

    @PostMapping("upload")
    public CommonResult<String> upload(MultipartFile file, String bucketName){
        String uploadPath = uploadFileManager.upload(file, bucketName);
        return StringUtils.hasText(uploadPath) ? CommonResult.successNoMessage(uploadPath) : CommonResult.failed();
    }
}
