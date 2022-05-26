package com.lucian.front.bean.translate;

import cn.hutool.extra.spring.SpringUtil;
import com.lucian.common.bean.entity.BaseEntity;
import com.lucian.common.bean.entity.Post;
import com.lucian.common.bean.entity.User;
import com.lucian.common.service.UploadFileService;
import com.lucian.common.utils.MinIoUtil;
import com.lucian.front.bean.vo.BlogIndexVO;
import com.lucian.front.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lingxiangdeng
 */
@Mapper(componentModel = "spring")
public abstract class Post2IndexVO {

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
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy年MM月dd日"),
    })
    public abstract BlogIndexVO convertToPostIndexVo(Post post);

    /**
     *  将posts数据库对象 转换为 一览对象
     * @param posts post list
     * @return 一览对象
     */
    public abstract List<BlogIndexVO> convertToBlogIndex(List<Post> posts);

    @AfterMapping
    protected void setUsername(List<Post> posts, @MappingTarget List<BlogIndexVO> blogIndexVO){
        Map<String, List<BlogIndexVO>> postWithUserId = blogIndexVO.stream()
                .filter(post -> Objects.nonNull(post.getUserId()))
                .collect(Collectors.toMap(BlogIndexVO::getUserId,
                        postVO -> new ArrayList<BlogIndexVO>(){{add(postVO);}}, (oldValue, newValue) -> {
            oldValue.addAll(newValue);
            return oldValue;
        }));
        // 提取用户id 去users表中查出userName
        Set<String> userIds = postWithUserId.keySet();
        if (userIds.size() == 0) {
            return;
        }
        UserService userService = SpringUtil.getBean(UserService.class);
        Map<String, String> userNameCollect = userService.listByIds(userIds).stream().collect(Collectors.toMap(BaseEntity::getId, User::getUsername));
        userNameCollect.forEach((userId, userName) -> {
            postWithUserId.get(userId).forEach(blog -> blog.setUsername(userName));
        });
    }

    @AfterMapping
    protected void setImageUrl(List<Post> posts, @MappingTarget List<BlogIndexVO> blogIndexVO) {
        Map<String, String> imageIdWithPostId = posts.stream().filter(post -> Objects.nonNull(post.getUploadFileId())).collect(Collectors.toMap(Post::getUploadFileId, BaseEntity::getId));
        Map<String, Post> postWithFileUpload = posts.stream().filter(post -> Objects.nonNull(post.getUploadFileId())).collect(Collectors.toMap(Post::getUploadFileId, post -> post));
        Set<String> fileUploadIds = postWithFileUpload.keySet();
        if (fileUploadIds.size() == 0) {
            return;
        }
        Map<String, BlogIndexVO> blogIdWithBlogIndexVO = blogIndexVO.stream().collect(Collectors.toMap(BlogIndexVO::getId, post -> post));
        Map<String, String> fileUploadCollect = uploadFileService.listByIds(fileUploadIds).stream().collect(Collectors.toMap(BaseEntity::getId, uploadFile -> MinIoUtil.findOne(uploadFile.getBucketName(), uploadFile.getFileName())));
        fileUploadCollect.forEach((fileUploadId, fileUploadUrl) -> {
            blogIdWithBlogIndexVO.get(imageIdWithPostId.get(fileUploadId)).setImageUrl(fileUploadUrl);
        });
    }
}
