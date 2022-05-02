package com.lucian.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucian.back.dao.TopPostDao;
import com.lucian.back.service.TopPostService;
import com.lucian.common.bean.entity.TopPost;
import org.springframework.stereotype.Service;

/**
 * @author lingxiangdeng
 */
@Service
public class TopPostServiceImpl extends ServiceImpl<TopPostDao, TopPost> implements TopPostService {
}
