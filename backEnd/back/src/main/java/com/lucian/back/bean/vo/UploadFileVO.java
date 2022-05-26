package com.lucian.back.bean.vo;

import lombok.Data;

/**
 * @author lingxiangdeng
 */
@Data
public class UploadFileVO {

    /**
     * 文件id 主键
     */
    private String id;

    /**
     * 文件地址
     */
    private String fileUrl;
}
