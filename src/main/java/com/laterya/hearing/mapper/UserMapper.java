package com.laterya.hearing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laterya.hearing.model.pojo.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper extends BaseMapper<User> {
    User checkByAccount(@Param("username") String username,
                        @Param("password") String password);
    User loginById(@Param("id") int id);
    User selectFriendById(@Param("id") int id);
    int insertUser(User user);
    String selectId(@Param("username") String username);
    String checkByName(String username);
    boolean updatePsw(@Param("username") String username,
                   @Param("password") String password,
                   @Param("new_Psw") String new_Psw);
    boolean updateSex(@Param("username") String username,
                      @Param("password") String password,
                      @Param("new_Sex") String new_Sex);
    boolean updateUsn(@Param("username") String username,
                      @Param("password") String password,
                      @Param("new_Usn") String new_Usn);
}
