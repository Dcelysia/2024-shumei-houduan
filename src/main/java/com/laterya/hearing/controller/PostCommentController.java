package com.laterya.hearing.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laterya.hearing.common.BaseResponse;
import com.laterya.hearing.common.DeleteRequest;
import com.laterya.hearing.common.ErrorCode;
import com.laterya.hearing.common.ResultUtils;
import com.laterya.hearing.model.dto.postcomment.CommentAddRequest;
import com.laterya.hearing.model.dto.postcomment.CommentListRequest;
import com.laterya.hearing.model.dto.postcomment.CommentUpdateRequest;
import com.laterya.hearing.model.pojo.PostComment;
import com.laterya.hearing.service.PostCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/postComment")
@Api(value = "帖子评论接口", tags = "帖子评论接口")
public class PostCommentController {
    @Resource
    private PostCommentService postCommentService;

    @PostMapping("/add")
    @ApiOperation(value = "创建评论", notes = "创建评论")
    public BaseResponse<Long> addComment(@RequestBody CommentAddRequest commentAddRequest) {
        PostComment postComment = new PostComment();
        postComment.setPostId(commentAddRequest.getPostId());
        postComment.setUserId(commentAddRequest.getUserId());
        postComment.setContent(commentAddRequest.getContent());
        boolean save = postCommentService.save(postComment);
        if (save) {
            return ResultUtils.success(postComment.getId());
        } else {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR);
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除评论", notes = "删除评论")
    public BaseResponse<Boolean> deleteComment(@RequestBody DeleteRequest commentId) {
        boolean remove = postCommentService.removeById(commentId);
        if (remove) {
            return ResultUtils.success(true);
        } else {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR);
        }
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新评论", notes = "更新评论")
    public BaseResponse<Boolean> updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        PostComment postComment = new PostComment();
        postComment.setId(commentUpdateRequest.getId());
        postComment.setPostId(commentUpdateRequest.getPostId());
        postComment.setUserId(commentUpdateRequest.getUserId());
        postComment.setContent(commentUpdateRequest.getContent());
        boolean update = postCommentService.saveOrUpdate(postComment);
        return ResultUtils.success(update);
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取评论", notes = "获取某一帖子的所有评论")
    public BaseResponse<List<PostComment>> getCommentList(@RequestBody CommentListRequest commentListRequest) {
        List<PostComment> postId = postCommentService.list(new QueryWrapper<PostComment>().eq("postId", commentListRequest.getPostId()));
        return ResultUtils.success(postId);
    }
}
