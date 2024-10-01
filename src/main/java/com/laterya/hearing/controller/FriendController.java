package com.laterya.hearing.controller;

import com.google.gson.Gson;
import com.laterya.hearing.model.pojo.Friend;
import com.laterya.hearing.response.newResponse;
import com.laterya.hearing.service.FriendService;
import com.laterya.hearing.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {
    private Gson gson=new Gson();
    @Autowired
    private FriendService friendService;
    @RequestMapping("/request")
    public String request(@RequestBody Friend friend){
        return friendService.request(friend.getFromId(),friend.getToId());
    }
    @RequestMapping("/agree")
    public String agree(@RequestBody Friend friend){
      friendService.agree(friend.getFromId(),friend.getToId());
      return gson.toJson(new Response(0,false,null));
    }
    @RequestMapping("/reject")
    public String reject(@RequestBody Friend friend){
        friendService.reject(friend.getFromId(),friend.getToId());
        return gson.toJson(new Response(0,false,null));
    }
    @RequestMapping("/friendList")
    public String friendList(@RequestBody Friend friend){
        List<Friend> list=friendService.friendList(friend.getToId());
        return gson.toJson(new Response(0,false,null,list));
    }
    @RequestMapping("/requestList")
    public String requestList(@RequestBody Friend friend){
        List<Friend> list=friendService.requestList(friend.getToId());
        return gson.toJson(new Response(0,false,null,list));
    }
    @RequestMapping("/delete")
    public String delete(@RequestBody Friend friend){
        boolean msg=friendService.deleteFriend(friend.getFromId(),friend.getToId());
        if(msg){
            return gson.toJson(new newResponse(0,false,null,true));
        }
       return gson.toJson(new Response(1,true,"非好友不能进行此项操作"));
    }
    @RequestMapping("/selectFriend")
    public String selectFriend(@RequestBody Friend friend){
        return gson.toJson(new Response(0,false,null,friendService.selectFriend(friend.getToId())));
    }
    @RequestMapping("/isFriend")
    public String isFriend(@RequestBody Friend friend){
        return gson.toJson(new newResponse(0,false,null, friendService.isFriend(friend.getFromId(),friend.getToId())));
    }
}
