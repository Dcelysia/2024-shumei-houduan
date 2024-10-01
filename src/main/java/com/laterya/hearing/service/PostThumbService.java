package com.laterya.hearing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laterya.hearing.model.pojo.PostThumb;


/**
 * 帖子点赞服务
 *
 */
public interface PostThumbService extends IService<PostThumb> {

    /**
     * 点赞
     *
     * @param postId
     * @return
     */
    int doPostThumb(long postId, long userId);

    /**
     * 帖子点赞（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostThumbInner(long userId, long postId);
}
