package com.laterya.hearing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.laterya.hearing.common.BaseResponse;
import com.laterya.hearing.common.ErrorCode;
import com.laterya.hearing.common.ResultUtils;
import com.laterya.hearing.exception.BusinessException;
import com.laterya.hearing.model.dto.post.PostQueryRequest;
import com.laterya.hearing.model.dto.postfavour.PostFavourAddRequest;
import com.laterya.hearing.model.pojo.Post;
import com.laterya.hearing.model.pojo.PostFavour;
import com.laterya.hearing.service.PostFavourService;
import com.laterya.hearing.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子收藏接口
 */
@RestController
@RequestMapping("/post_favour")
@Slf4j
@Api(value = "帖子收藏接口", tags = "帖子收藏接口")
public class PostFavourController {

    @Resource
    private PostFavourService postFavourService;

    @Resource
    private PostService postService;


    /**
     * 收藏 / 取消收藏
     *
     * @param postFavourAddRequest
     * @return resultNum 收藏变化数
     */
    @PostMapping("/")
    @ApiOperation(value = "收藏 / 取消收藏", notes = "收藏 / 取消收藏")
    public BaseResponse<Integer> doPostFavour(@RequestBody PostFavourAddRequest postFavourAddRequest) {
        if (postFavourAddRequest == null || postFavourAddRequest.getPostId() <= 0 || postFavourAddRequest.getUserId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = postFavourService.doPostFavour(postFavourAddRequest.getPostId(), postFavourAddRequest.getUserId());
        return ResultUtils.success(result);
    }

    /**
     * 获取我收藏的帖子列表
     *
     * @param postQueryRequest
     */
    @PostMapping("/my/list")
    @ApiOperation(value = "获取我收藏的帖子列表", notes = "获取我收藏的帖子列表")
    public BaseResponse<List<Post>> listMyFavourPostByPage(@RequestBody PostQueryRequest postQueryRequest) {
        if (postQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<PostFavour> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostFavour::getUserId, postQueryRequest.getUserId());
        List<PostFavour> list = postFavourService.list(queryWrapper);
        // 获取收藏的帖子 id 列表
        List<Long> ids = list.stream().map(PostFavour::getPostId).collect(Collectors.toList());
        List<Post> postList = postService.listByIds(ids);
        return ResultUtils.success(postList);
    }
}
