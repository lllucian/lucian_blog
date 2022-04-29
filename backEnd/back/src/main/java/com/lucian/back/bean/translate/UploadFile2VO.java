package com.lucian.back.bean.translate;

import com.lucian.back.bean.vo.UploadFileVO;
import com.lucian.back.utils.MinIoUtil;
import com.lucian.common.bean.entity.UploadFile;
import org.mapstruct.*;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public abstract class UploadFile2VO {
    /**
     *
     * @param uploadFile
     * @return
     */
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "fileUrl", ignore = true)
    })
    public abstract UploadFileVO translate2VO(UploadFile uploadFile);

    /**
     *
     * @param uploadFile
     * @param uploadFileVO
     */
    @AfterMapping
    public void setFileUploadUrl(UploadFile uploadFile, @MappingTarget UploadFileVO uploadFileVO){
        uploadFileVO.setFileUrl(MinIoUtil.findOne(uploadFile.getBucketName(), uploadFile.getFileName()));
    }
}
