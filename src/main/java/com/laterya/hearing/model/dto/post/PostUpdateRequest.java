package com.laterya.hearing.model.dto.post;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 更新请求
 *
 */
@Data
public class PostUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

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