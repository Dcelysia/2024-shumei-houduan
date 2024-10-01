package com.laterya.hearing.model.dto.postthumb;

import lombok.Data;

import java.io.Serializable;

/**
 * 帖子点赞请求
 *
 */
@Data
public class PostThumbAddRequest implements Serializable {

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}