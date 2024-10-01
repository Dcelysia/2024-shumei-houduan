package com.laterya.hearing.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laterya.hearing.model.pojo.Post;
import com.laterya.hearing.model.vo.PostVO;

import java.util.List;

/**
 * 帖子服务
 *
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     *
     * @param post
     * @param add
     */
    void validPost(Post post, boolean add);


    /**
     * 获取帖子封装
     *
     * @param post
     * @return
     */
    PostVO getPostVO(Post post, Long loginUserId);

    List<Post> getPagePost(long current, long size);

    /**
     * 分页获取帖子封装
     *
     * @param postPage
     * @return
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, Long loginUserId);

}
