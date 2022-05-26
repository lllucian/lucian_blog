package com.lucian.front.bean.translate;

import cn.hutool.extra.spring.SpringUtil;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.UploadFile;
import com.lucian.common.bean.entity.User;
import com.lucian.common.service.UploadFileService;
import com.lucian.common.utils.MinIoUtil;
import com.lucian.front.bean.vo.BlogDetailVO;
import com.lucian.front.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @author denglingxiang
 * @date 2022/05/16 14:26
 **/
@Mapper(componentModel = "spring")
public abstract class Post2DetailVO {
    UploadFileService uploadFileService;

    @Autowired
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    /**
     * 将post数据库对象 转换为 一览对象
     * @param post 数据库对象
     * @return 一览对象
     */
    @Mappings({
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy年MM月dd日"),
            @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy年MM月dd日"),
            @Mapping(target = "imageUrl", ignore = true),
            @Mapping(target = "username", ignore = true)
    })
    public abstract BlogDetailVO convertToPostDetailVo(Post post);

    /**
     * 设置用户名
     * @param post 文章bean
     * @param blogDetailVO 文章vo
     */
    @AfterMapping
    protected void setUsername(Post post, @MappingTarget BlogDetailVO blogDetailVO){
        // 获取用户id
        String userId = post.getUserId();
        if (Objects.isNull(userId)) {
            return;
        }
        UserService userService = SpringUtil.getBean(UserService.class);
        User user = userService.getById(userId);
        if (Objects.isNull(user)){
            return;
        }
        // 设置用户名
        blogDetailVO.setUsername(user.getUsername());
    }

    @AfterMapping
    protected void setImageUrl(Post post, @MappingTarget BlogDetailVO blogDetailVO) {
        String uploadFileId = post.getUploadFileId();
        if (Objects.isNull(uploadFileId)){
            return;
        }
        UploadFile uploadFile = uploadFileService.getById(uploadFileId);
        if (Objects.isNull(uploadFile)){
            return;
        }
        blogDetailVO.setImageUrl(MinIoUtil.findOne(uploadFile.getBucketName(), uploadFile.getFileName()));
    }
}
