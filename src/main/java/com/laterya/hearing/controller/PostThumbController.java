package com.laterya.hearing.controller;


import com.laterya.hearing.common.BaseResponse;
import com.laterya.hearing.common.ErrorCode;
import com.laterya.hearing.common.ResultUtils;
import com.laterya.hearing.exception.BusinessException;
import com.laterya.hearing.model.dto.postthumb.PostThumbAddRequest;
import com.laterya.hearing.service.PostThumbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 帖子点赞接口
 *
 */
@RestController
@RequestMapping("/post_thumb")
@Slf4j
@Api(value = "帖子点赞接口", tags = "帖子点赞接口")
public class PostThumbController {

    @Resource
    private PostThumbService postThumbService;

    /**
     * 点赞 / 取消点赞
     *
     * @param postThumbAddRequest
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/")
    @ApiOperation(value = "点赞 / 取消点赞", notes = "点赞 / 取消点赞")
    public BaseResponse<Integer> doThumb(@RequestBody PostThumbAddRequest postThumbAddRequest) {
        if (postThumbAddRequest == null || postThumbAddRequest.getPostId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        long postId = postThumbAddRequest.getPostId();
        int result = postThumbService.doPostThumb(postId, postThumbAddRequest.getUserId());
        return ResultUtils.success(result);
    }

}
