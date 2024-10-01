package com.laterya.hearing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laterya.hearing.model.pojo.User;
import org.apache.ibatis.annotations.Param;
import com.laterya.hearing.model.vo.UserVO;

public interface UserService extends IService<User> {

    int insertUser(User user);
    User checkByAccount(String username, String password) throws Exception;
    User loginById(int id);
    User selectFriendById(int id);
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

    UserVO getUserVO(User user);
}
