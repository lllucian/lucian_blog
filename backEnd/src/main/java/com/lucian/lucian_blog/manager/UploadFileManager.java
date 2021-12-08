package com.lucian.lucian_blog.manager;

import com.lucian.lucian_blog.bean.entity.UploadFile;
import com.lucian.lucian_blog.config.FastDFSClient;
import com.lucian.lucian_blog.service.UploadFileService;
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

    public String upload(MultipartFile file){
        String[] fastInfo = FastDFSClient.uploadFile(file);
        if (fastInfo == null) return null;
        String originalFilename = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        UploadFile uploadFile = new UploadFile.Builder().fileName(originalFilename).extName(extension).groupName(fastInfo[0]).filePath(fastInfo[1]).build();
        uploadFileService.save(uploadFile);
        return uploadFile.getFastDFSPath(FastDFSClient.getTrackerUrl());
    }
}
