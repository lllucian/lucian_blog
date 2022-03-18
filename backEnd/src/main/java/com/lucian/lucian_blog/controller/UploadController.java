package com.lucian.lucian_blog.controller;

import com.lucian.lucian_blog.bean.vo.UploadFileVO;
import com.lucian.lucian_blog.common.CommonResult;
import com.lucian.lucian_blog.manager.UploadFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("admin/upload_file")
public class UploadController {
    UploadFileManager uploadFileManager;

    @Autowired
    public void setUploadFileManager(UploadFileManager uploadFileManager) {
        this.uploadFileManager = uploadFileManager;
    }

    @PostMapping("upload")
    public CommonResult<UploadFileVO> upload(MultipartFile file, String bucketName){
        UploadFileVO upload = uploadFileManager.upload(file, bucketName);
        return Objects.nonNull(upload) ? CommonResult.successNoMessage(upload) : CommonResult.failed();
    }

    @GetMapping("get_file_url/{id}")
    public CommonResult<String> getFileURL(@PathVariable("id") Integer fileId){
        String fileURL = uploadFileManager.getFileURL(fileId);
        return StringUtils.hasText(fileURL) ? CommonResult.successNoMessage(fileURL) : CommonResult.failed();
    }
}
