package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    private UploadFile(Builder builder){
        this.userId = builder.userId;
        this.controllerName = builder.controllerName;
        this.fileName = builder.fileName;
        this.extName = builder.extName;
        this.bucketName = builder.bucketName;
    }

    public static class Builder {
        // 上传者
        private  Integer userId;
        // 上传的controller
        private  String controllerName;
        // 上传文件名
        private  String fileName;
        // 上传文件拓展名
        private  String extName;
        // 上传bucketName
        private  String bucketName;
        // 上传至fastdfs的路径
        private  String filePath;


        public Builder() {
        }

        public Builder userId(Integer userId){
            this.userId = userId;
            return this;
        }

        public Builder controllerName(String controllerName){
            this.controllerName = controllerName;
            return this;
        }

        public Builder fileName(String fileName){
            this.fileName = fileName;
            return this;
        }

        public Builder extName(String extName){
            this.extName = extName;
            return this;
        }

        public Builder bucketName(String bucketName){
            this.bucketName = bucketName;
            return this;
        }

        public Builder filePath(String filePath){
            this.filePath = filePath;
            return this;
        }

        public UploadFile build() {
            return new UploadFile(this);
        }
    }
}
