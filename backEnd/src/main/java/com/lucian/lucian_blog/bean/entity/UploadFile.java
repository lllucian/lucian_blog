package com.lucian.lucian_blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

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
    @TableField("fdfs_group_name")
    private String groupName;
    // 上传至fastdfs的路径
    @TableField("fdfs_file_path")
    private String filePath;

    private UploadFile(Builder builder){
        this.userId = builder.userId;
        this.controllerName = builder.controllerName;
        this.fileName = builder.fileName;
        this.extName = builder.extName;
        this.groupName = builder.groupName;
        this.filePath = builder.filePath;
    }

    public String getFastDFSPath(String trackerUrl){
        return StringUtils.hasText(groupName) && StringUtils.hasText(filePath) ? String.format("%s/%s/%s", trackerUrl, groupName, filePath) : null;
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
        // 上传至fastdfs的group名
        private  String groupName;
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

        public Builder groupName(String groupName){
            this.groupName = groupName;
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
