package com.laterya.hearing.model.dto.post;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询请求
 *
 */
@Data
public class PostQueryRequest implements Serializable {

    /**
     * 创建用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}