package com.laterya.hearing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.laterya.hearing.common.BaseResponse;
import com.laterya.hearing.common.DeleteRequest;
import com.laterya.hearing.common.ErrorCode;
import com.laterya.hearing.common.ResultUtils;
import com.laterya.hearing.exception.BusinessException;
import com.laterya.hearing.exception.ThrowUtils;
import com.laterya.hearing.model.dto.post.*;
import com.laterya.hearing.model.pojo.Post;
import com.laterya.hearing.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帖子接口
 */
@RestController
@RequestMapping("/post")
@Slf4j
@Api(value = "帖子接口", tags = "帖子接口")
public class PostController {

    @Resource
    private PostService postService;

    private final static Gson GSON = new Gson();

    // region 增删改查

    /**
     * 创建
     *
     * @param postAddRequest
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "创建帖子", notes = "创建帖子")
    public BaseResponse<Long> addPost(@RequestBody PostAddRequest postAddRequest) {
        if (postAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long userId = postAddRequest.getUserId();
        Post post = new Post();

        post.setTitle(postAddRequest.getTitle());
        post.setContent(postAddRequest.getContent());

        postService.validPost(post, true);
        post.setUserId(userId);
        post.setFavourNum(0);
        post.setThumbNum(0);
        boolean result = postService.save(post);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newPostId = post.getId();
        return ResultUtils.success(newPostId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除帖子", notes = "删除帖子")
    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = deleteRequest.getId();
        boolean b = postService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * @param postUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新帖子", notes = "更新帖子")
    public BaseResponse<Boolean> updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
        if (postUpdateRequest == null || postUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postUpdateRequest, post);
        // 参数校验
        postService.validPost(post, false);
        long id = postUpdateRequest.getId();
        // 判断是否存在
        Post oldPost = postService.getById(id);
        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = postService.updateById(post);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation(value = "根据 id 获取帖子", notes = "根据 id 获取帖子")
    public BaseResponse<Post> getPostVOById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Post post = postService.getById(id);
        if (post == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(postService.getById(id));
    }

    /**
     * 分页获取列表（封装类）
     ** @return
     */
    @PostMapping("/list/page")
    @ApiOperation(value = "分页获取帖子列表", notes = "分页获取帖子列表")
    public BaseResponse<List<Post>> listPostVOByPage(@RequestBody PostPageRequest postPageRequest) {
        long current = postPageRequest.getCurrent();
        long size = postPageRequest.getSize();
        // 限制爬虫
        List<Post> postList = postService.getPagePost(current, size);
        return ResultUtils.success(postList);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取帖子列表", notes = "获取帖子列表")
    public BaseResponse<List<Post>> listPost() {
        List<Post> postList = postService.list();
        return ResultUtils.success(postList);
    }

    @PostMapping("/search/list")
    @ApiOperation(value = "搜索帖子", notes = "搜索帖子列表")
    public BaseResponse<List<Post>> searchListPost(@RequestBody SearchPostRequest searchPostRequest) {
        LambdaQueryWrapper<Post> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(searchPostRequest.getTitle() != null, Post::getTitle, searchPostRequest.getTitle());
        lambdaQueryWrapper.like(searchPostRequest.getContent() != null, Post::getContent, searchPostRequest.getContent());
        lambdaQueryWrapper.eq(searchPostRequest.getUserId() != null, Post::getUserId, searchPostRequest.getUserId());
        List<Post> postList = postService.list(lambdaQueryWrapper);
        return ResultUtils.success(postList);
    }


    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param postQueryRequest
     * @return
     */
    @PostMapping("/my/list")
    @ApiOperation(value = "获取当前用户创建的帖子列表", notes = "获取当前用户创建的帖子列表")
    public BaseResponse<List<Post>> listMyPostVOByPage(@RequestBody PostQueryRequest postQueryRequest) {
        List<Post> postList = postService.list(new QueryWrapper<Post>()
                .eq("userId", postQueryRequest.getUserId())
                .orderByDesc("createTime"));
        return ResultUtils.success(postList);
    }

    // endregion


}
