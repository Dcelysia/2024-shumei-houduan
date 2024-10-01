package com.laterya.hearing.service.impl;

import com.google.gson.Gson;
import com.laterya.hearing.mapper.FriendMapper;
import com.laterya.hearing.mapper.UserMapper;
import com.laterya.hearing.model.pojo.Friend;
import com.laterya.hearing.model.pojo.User;
import com.laterya.hearing.response.Response;
import com.laterya.hearing.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FriendServiceImpl implements FriendService {
    private Gson gson=new Gson();

    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isFriend(int fromId, int toId) {
        String msg1=friendMapper.select(fromId,toId);
        String msg2=friendMapper.select(toId,fromId);
       if(msg1==null){
           if(msg2==null){
               return false;
           }
           else if(msg2.equals("1")){
               return true;
           }
           return false;
       }
       else {
           if(msg2==null){
               if(msg1.equals("1")){
                   return true;
               }
               return false;
           }
           else{
               if(msg1.equals("1")||msg2.equals("1")){
                   return true;
               }
               return false;
           }
       }

    }

    @Override
    public User selectFriend(int Id) {
        return userMapper.selectFriendById(Id);
    }

    @Override
    public String request(int fromId, int toId) {
        if(fromId>0&&toId>0&&fromId!=toId){
            String msg=friendMapper.select(fromId,toId);
            if(msg!=null){
                if(msg.equals("0")){
                    return gson.toJson(new Response(1,true,"已建立申请链接，对方暂未回复响应"));
                }
                else {
                    return gson.toJson(new Response(1,true,"已为好友，请勿重复添加"));
                }
            }
            else{
                friendMapper.request(fromId,toId);
                return gson.toJson(new Response(0,false,null));
            }
        }
        return gson.toJson(new Response(1,true,"请输入正确的id格式(＞0)或者id相同引起冲突"));
    }

    @Override
    public boolean deleteFriend(int fromId, int toId) {
        if(friendMapper.select(fromId,toId)==null&&friendMapper.select(toId,fromId)==null){
            return false;
        }
        else if(!isFriend(fromId,toId)){
            return false;
        }
        friendMapper.reject(fromId,toId);
        friendMapper.reject(toId,fromId);
        return true;
    }

    @Override
    public List<Friend> requestList(int Id) {
        List<Friend> list=friendMapper.requestList(Id);
        return list;
    }

    @Override
    public List<Friend> friendList(int Id) {
        List<Friend> list=friendMapper.friendList(Id);
        System.out.println(list.size());
        return list;
    }

    @Override
    public void agree(int fromId, int toId) {
        friendMapper.agree(fromId,toId);
    }

    @Override
    public void reject(int fromId, int toId) {
        friendMapper.reject(fromId,toId);
    }


}
