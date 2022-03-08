package com.lucian.lucian_blog.manager;

import com.lucian.lucian_blog.bean.entity.UploadFile;
import com.lucian.lucian_blog.service.UploadFileService;
import com.lucian.lucian_blog.utils.MinIoUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFileManager {
    UploadFileService uploadFileService;

    @Autowired
    public void setUploadFileService(UploadFileService uploadFileService){
        this.uploadFileService = uploadFileService;
    }

    @SneakyThrows(Exception.class)
    public String upload(MultipartFile file, String bucketName){
        String uploadFileURL = MinIoUtil.upload(bucketName, file.getOriginalFilename(), file.getInputStream());
        String originalFilename = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        UploadFile uploadFile = new UploadFile.Builder().fileName(originalFilename).extName(extension).bucketName(bucketName).build();
        uploadFileService.save(uploadFile);
        return uploadFileURL;
    }
}
