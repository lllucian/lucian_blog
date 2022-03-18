package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("upload_files")
public class UploadFile extends BaseEntity{
    // 上传者
    private Integer userId;
    // 上传的controller
    private String controllerName;
    // 上传文件名
    private String fileName;
    // 上传文件拓展名
    private String extName;
    // 上传至fastdfs的group名
    private String bucketName;

    private String realName;
}
