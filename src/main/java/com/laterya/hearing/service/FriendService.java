package com.laterya.hearing.service;

import com.laterya.hearing.model.pojo.Friend;
import com.laterya.hearing.model.pojo.User;

import java.util.List;

public interface FriendService {
    boolean isFriend(int fromId,int toId);
    User selectFriend(int Id);
    String request(int fromId,int toId);
    boolean deleteFriend(int fromId,int toId);
    List<Friend> requestList(int Id);
    List<Friend> friendList(int Id);
    void agree(int fromId,int toId);
    void reject(int fromId,int toId);
}
