package com.laterya.hearing.model.dto.postcomment;

import lombok.Data;

@Data
public class CommentUpdateRequest {

    /**
     * 评论 id
     */
    private Long id;

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;


}
