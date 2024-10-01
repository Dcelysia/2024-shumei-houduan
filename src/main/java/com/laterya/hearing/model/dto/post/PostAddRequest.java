package com.laterya.hearing.model.dto.post;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 *
 */
@Data
public class PostAddRequest implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}