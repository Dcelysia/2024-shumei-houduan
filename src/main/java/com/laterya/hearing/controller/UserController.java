package com.laterya.hearing.controller;


import cn.hutool.core.util.RandomUtil;
import com.google.gson.Gson;
import com.laterya.hearing.common.DeleteRequest;
import com.laterya.hearing.model.dto.user.AddEmergencyNumberRequest;
import com.laterya.hearing.model.dto.user.UserRegisterRequest;
import com.laterya.hearing.model.pojo.User;
import com.laterya.hearing.response.Response;
import com.laterya.hearing.service.UserService;
import com.laterya.hearing.util.SmsUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;


@RestController
@RequestMapping("/user")
public class UserController {
    private Gson gson = new Gson();
    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception {
        return gson.toJson(new Response(0, false, null, userService.checkByAccount(user.getUsername(), user.getPassword())));
    }

    @PostMapping("/send")
    @ApiOperation("发送验证码")
    public String send(@RequestBody String phone) throws Exception {
        try {
            String code = RandomUtil.randomNumbers(6);
            stringRedisTemplate.opsForValue().set("login:code:" + phone, code, Duration.ofMinutes(3));
            SmsUtil.sendMessage(phone, code);
        } catch (Exception e) {
            return gson.toJson(new Response(1, true, "发送失败"));
        }
        return gson.toJson(new Response(0, false, null));
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        User user = new User();
        if (userRegisterRequest.getPassword().length() <= 0 || userRegisterRequest.getPassword().length() > 12 || userRegisterRequest.getUserName().length() > 12 || userRegisterRequest.getUserName().length() <= 0 || (!userRegisterRequest.getSex().equals("1") && !userRegisterRequest.getSex().equals("0"))) {
            return gson.toJson(new Response(1, true, "填写格式有问题"));
        } else {
            if (userRegisterRequest.getSex().equals("0")) {
                user.setSex("男");
            } else {
                userRegisterRequest.setSex("女");
            }
            if (userService.checkByName(user.getUsername()) != null) {
                return gson.toJson(new Response(1, true, "该用户名已被注册"));
            }
            String code = userRegisterRequest.getCode();
            String realCode = stringRedisTemplate.opsForValue().get("login:code:" + userRegisterRequest.getPhone());
            if (realCode == null || !realCode.equals(code)) {
                return gson.toJson(new Response(1, true, "验证码错误"));
            }
            user.setUsername(userRegisterRequest.getUserName());
            user.setPassword(userRegisterRequest.getPassword());
            user.setPhone(userRegisterRequest.getPhone());
            userService.insertUser(user);
            return gson.toJson(new Response(0, false, null));
        }
    }

    @PostMapping("/addEmergencyNumber")
    public String addEmergencyNumber(@RequestBody AddEmergencyNumberRequest addEmergencyNumberRequest) {
        Long userId = addEmergencyNumberRequest.getUserId();
        String emergencyNumber = addEmergencyNumberRequest.getEmergencyNumber();

        User user = userService.getById(userId);
        user.setEmergencyNumber(emergencyNumber);

        boolean b = userService.updateById(user);
        if (b) {
            return gson.toJson(new Response(0, false, null));
        } else {
            return gson.toJson(new Response(1, true, "添加失败"));
        }
    }

    @PostMapping("/deleteEmergencyNumber")
    public String deleteEmergencyNumber(@RequestBody DeleteRequest deleteRequest) {
        Long id = deleteRequest.getId();

        User user = userService.getById(id);
        user.setEmergencyNumber(null);
        boolean b = userService.updateById(user);
        if (b) {
            return gson.toJson(new Response(0, false, null));
        } else {
            return gson.toJson(new Response(1, true, "删除失败"));
        }
    }

    @GetMapping("/getEmergencyNumber")
    public String getEmergencyNumber(@RequestParam Long id) {
        User user = userService.getById(id);
        return gson.toJson(new Response(0, false, null, user.getEmergencyNumber()));
    }


    @PostMapping("/updatePsw")
    public String updatePsw(@RequestBody User user) {
        if (userService.updatePsw(user.getUsername(), user.getPassword(), user.getNew_Psw())) {
            return gson.toJson(new Response(0, false, null));
        }
        return gson.toJson(new Response(1, true, "修改密码失败，请检查账号或密码"));
    }

    @PostMapping("/updateSex")
    public String updateSex(@RequestBody User user) {
        if (userService.updateSex(user.getUsername(), user.getPassword(), user.getNew_Sex())) {
            return gson.toJson(new Response(0, false, null));
        }
        return gson.toJson(new Response(1, true, "修改性别失败，请检查账号或密码"));
    }

    @PostMapping("/updateUsn")
    public String updateUsn(@RequestBody User user) {
        if (userService.updateUsn(user.getUsername(), user.getPassword(), user.getNew_Usn())) {
            return gson.toJson(new Response(0, false, null));
        }
        return gson.toJson(new Response(1, true, "修改用户名失败，请检查账号或密码"));
    }

    @GetMapping("/selectId")
    public String selectId(@RequestBody User user) {
        String array = userService.selectId(user.getUsername());
        if (array != null) {
            return gson.toJson(new Response(0, false, null, array));
        }
        return gson.toJson(new Response(1, true, "未查找到该用户id"));
    }

}
