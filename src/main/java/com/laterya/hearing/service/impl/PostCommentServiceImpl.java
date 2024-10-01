package com.laterya.hearing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laterya.hearing.model.pojo.PostComment;
import com.laterya.hearing.service.PostCommentService;
import com.laterya.hearing.mapper.PostCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author yuxia
* @description 针对表【post_comment(帖子评论)】的数据库操作Service实现
* @createDate 2023-05-20 14:45:18
*/
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment>
    implements PostCommentService{

}




