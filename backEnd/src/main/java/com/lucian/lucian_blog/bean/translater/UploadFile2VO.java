package com.lucian.lucian_blog.bean.translater;

import com.lucian.lucian_blog.bean.entity.UploadFile;
import com.lucian.lucian_blog.bean.vo.UploadFileVO;
import com.lucian.lucian_blog.utils.MinIoUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UploadFile2VO {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "fileUrl", ignore = true)
    })
    public abstract UploadFileVO translate2VO(UploadFile uploadFile);

    @AfterMapping
    public void setFileUploadUrl(UploadFile uploadFile, @MappingTarget UploadFileVO uploadFileVO){
        uploadFileVO.setFileUrl(MinIoUtil.findOne(uploadFile.getBucketName(), uploadFile.getFileName()));
    }
}
