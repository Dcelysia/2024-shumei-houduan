package com.laterya.hearing.model.dto.post;

import lombok.Data;

@Data
public class SearchPostRequest {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建用户 id
     */
    private Long userId;

}
