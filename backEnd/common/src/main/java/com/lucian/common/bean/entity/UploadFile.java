package com.lucian.common.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 上传文件实体类
 * @author lingxiangdeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("upload_files")
public class UploadFile extends BaseEntity {
    /**
     * 上传者id
     */
    private Integer userId;
    /**
     * 上传的controller
     */
    private String controllerName;
    /**
     * 在minio中存储的名字
     */
    private String fileName;
    /**
     * 上传的拓展名
     */
    private String extName;
    /**
     * minio bucketName
     */
    private String bucketName;

    /**
     * 上传的文件名
     */
    private String realName;
}
