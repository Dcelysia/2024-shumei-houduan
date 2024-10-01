package com.laterya.hearing.mapper;

import com.laterya.hearing.model.pojo.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {

    void request(@Param("fromId") int fromId,@Param("toId") int toId);

    String select(@Param("fromId") int fromId,@Param("toId") int toId);
    List<Friend> friendList(@Param("Id") int Id);
    List<Friend> requestList(@Param("Id") int Id);

    void agree(@Param("fromId") int fromId,@Param("toId") int toId);
    void reject(@Param("fromId") int fromId,@Param("toId") int toId);
}
