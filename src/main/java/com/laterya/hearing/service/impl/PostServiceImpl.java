package com.laterya.hearing.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.laterya.hearing.common.ErrorCode;
import com.laterya.hearing.exception.BusinessException;
import com.laterya.hearing.exception.ThrowUtils;
import com.laterya.hearing.mapper.PostFavourMapper;
import com.laterya.hearing.mapper.PostMapper;
import com.laterya.hearing.mapper.PostThumbMapper;
import com.laterya.hearing.model.pojo.Post;
import com.laterya.hearing.model.pojo.PostFavour;
import com.laterya.hearing.model.pojo.PostThumb;
import com.laterya.hearing.model.pojo.User;
import com.laterya.hearing.model.vo.PostVO;
import com.laterya.hearing.model.vo.UserVO;
import com.laterya.hearing.service.PostService;
import com.laterya.hearing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 帖子服务实现
 */
@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    private final static Gson GSON = new Gson();

    @Resource
    private UserService userService;

    @Resource
    private PostMapper postMapper;

    @Resource
    private PostThumbMapper postThumbMapper;

    @Resource
    private PostFavourMapper postFavourMapper;


    @Override
    public void validPost(Post post, boolean add) {
        if (post == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String title = post.getTitle();
        String content = post.getContent();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(title, content), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(title) && title.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }


    @Override
    public PostVO getPostVO(Post post, Long loginUserId) {
        PostVO postVO = PostVO.objToVo(post);
        long postId = post.getId();
        // 1. 关联查询用户信息
        Long userId = post.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        postVO.setUser(userVO);
        // 2. 已登录，获取用户点赞、收藏状态
        // 获取点赞
        QueryWrapper<PostThumb> postThumbQueryWrapper = new QueryWrapper<>();
        postThumbQueryWrapper.in("postId", postId);
        postThumbQueryWrapper.eq("userId", loginUserId);
        PostThumb postThumb = postThumbMapper.selectOne(postThumbQueryWrapper);
        postVO.setHasThumb(postThumb != null);
        // 获取收藏
        QueryWrapper<PostFavour> postFavourQueryWrapper = new QueryWrapper<>();
        postFavourQueryWrapper.in("postId", postId);
        postFavourQueryWrapper.eq("userId", loginUserId);
        PostFavour postFavour = postFavourMapper.selectOne(postFavourQueryWrapper);
        postVO.setHasFavour(postFavour != null);
        return postVO;
    }

    @Override
    public List<Post> getPagePost(long current, long size) {

        return postMapper.listPagePost(current, size);
    }

    @Override
    public Page<PostVO> getPostVOPage(Page<Post> postPage, Long loginUserId) {
        List<Post> postList = postPage.getRecords();
        Page<PostVO> postVOPage = new Page<>(postPage.getCurrent(), postPage.getSize(), postPage.getTotal());
        if (CollectionUtils.isEmpty(postList)) {
            return postVOPage;
        }
        // 1. 关联查询用户信息
        Set<Long> userIdSet = postList.stream().map(Post::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));
        // 2. 已登录，获取用户点赞、收藏状态
        Map<Long, Boolean> postIdHasThumbMap = new HashMap<>();
        Map<Long, Boolean> postIdHasFavourMap = new HashMap<>();
        if (loginUserId != null) {
            Set<Long> postIdSet = postList.stream().map(Post::getId).collect(Collectors.toSet());
            // 获取点赞
            QueryWrapper<PostThumb> postThumbQueryWrapper = new QueryWrapper<>();
            postThumbQueryWrapper.in("postId", postIdSet);
            postThumbQueryWrapper.eq("userId", loginUserId);
            List<PostThumb> postPostThumbList = postThumbMapper.selectList(postThumbQueryWrapper);
            postPostThumbList.forEach(postPostThumb -> postIdHasThumbMap.put(postPostThumb.getPostId(), true));
            // 获取收藏
            QueryWrapper<PostFavour> postFavourQueryWrapper = new QueryWrapper<>();
            postFavourQueryWrapper.in("postId", postIdSet);
            postFavourQueryWrapper.eq("userId", loginUserId);
            List<PostFavour> postFavourList = postFavourMapper.selectList(postFavourQueryWrapper);
            postFavourList.forEach(postFavour -> postIdHasFavourMap.put(postFavour.getPostId(), true));
        }
        // 填充信息
        List<PostVO> postVOList = postList.stream().map(post -> {
            PostVO postVO = PostVO.objToVo(post);
            Long userId = post.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            postVO.setUser(userService.getUserVO(user));
            postVO.setHasThumb(postIdHasThumbMap.getOrDefault(post.getId(), false));
            postVO.setHasFavour(postIdHasFavourMap.getOrDefault(post.getId(), false));
            return postVO;
        }).collect(Collectors.toList());
        postVOPage.setRecords(postVOList);
        return postVOPage;
    }

}




