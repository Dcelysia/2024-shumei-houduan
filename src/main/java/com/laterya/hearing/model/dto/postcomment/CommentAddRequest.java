package com.laterya.hearing.model.dto.postcomment;

import lombok.Data;

import java.util.Date;

@Data
public class CommentAddRequest {
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
