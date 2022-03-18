package com.lucian.lucian_blog.manager;

import com.lucian.lucian_blog.bean.entity.UploadFile;
import com.lucian.lucian_blog.bean.translater.UploadFile2VO;
import com.lucian.lucian_blog.bean.vo.UploadFileVO;
import com.lucian.lucian_blog.service.UploadFileService;
import com.lucian.lucian_blog.utils.MinIoUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class UploadFileManager {
    UploadFileService uploadFileService;

    @Autowired
    UploadFile2VO uploadFile2VO;

    @Autowired
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @SneakyThrows(Exception.class)
    public UploadFileVO upload(MultipartFile file, String bucketName) {
        String realName = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(realName);
        String fileName = UUID.randomUUID() + "." + extension;
        MinIoUtil.upload(bucketName, fileName, file.getInputStream());

        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(fileName);
        uploadFile.setExtName(extension);
        uploadFile.setRealName(realName);
        uploadFile.setBucketName(bucketName);
        uploadFileService.save(uploadFile);
        return uploadFile2VO.translate2VO(uploadFile);
    }


    public String getFileURL(Integer fileId) {
        UploadFile uploadFile = uploadFileService.getById(fileId);
        return MinIoUtil.findOne(uploadFile.getBucketName(), uploadFile.getFileName());
    }
}
