package com.laterya.hearing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laterya.hearing.model.vo.UserVO;
import com.laterya.hearing.service.UserService;
import com.laterya.hearing.mapper.UserMapper;
import com.laterya.hearing.model.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User checkByAccount(String username, String password) throws Exception {
        User user =userMapper.checkByAccount(username,password);
        return user;
    }

    @Override
    public User loginById(int id) {
        User user=userMapper.loginById(id);
        return user;
    }
    @Override
    public User selectFriendById(int id) {
        User user=userMapper.selectFriendById(id);
        return user;
    }

    @Override
    public String selectId(String username) {
        username="%"+username+"%";
        return userMapper.selectId(username);
    }

    @Override
    public String checkByName(String username) {
        return userMapper.checkByName(username);
    }

    @Override
    public boolean updatePsw(String username, String password, String new_Psw) {
        return userMapper.updatePsw(username,password,new_Psw);
    }

    @Override
    public boolean updateSex(String username, String password, String new_Sex) {
        return userMapper.updateSex(username,password,new_Sex);
    }

    @Override
    public boolean updateUsn(String username, String password, String new_Usn) {
        return userMapper.updateUsn(username,password,new_Usn);
    }

    @Override
    public UserVO getUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUsername(user.getUsername());
        userVO.setSex(user.getSex());
        return userVO;
    }
}
