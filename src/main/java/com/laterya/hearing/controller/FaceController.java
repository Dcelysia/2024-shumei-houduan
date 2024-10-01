package com.laterya.hearing.controller;

import com.google.gson.Gson;
import com.laterya.hearing.model.pojo.User;
import com.laterya.hearing.service.FaceService;
import com.laterya.hearing.service.UserService;
import com.laterya.hearing.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/face")
public class FaceController {
    //
    private Gson gson=new Gson();
    @Autowired
    private FaceService faceService;
    @Autowired
    private UserService userService;
    @RequestMapping("/addFace")
    public String addFace(@RequestBody User user){
        String result=faceService.addFace(user.getBytes(),user.getId());
        if(result==null){
            return gson.toJson(new Response(1,true,"添加失败"));
        }
        return result;
    }
    @RequestMapping("/compareFace")
    public String compareFace(@RequestBody User user){
        String resultId=faceService.compareFace(user.getBytes());
        if(resultId!=null){
            List<User> list= Collections.singletonList(userService.loginById(Integer.parseInt(resultId)));
            return gson.toJson(new Response(0,false,null,new User(list.get(0).getId(),list.get(0).getUsername(),list.get(0).getPassword(),list.get(0).getSex())));
        }
        return gson.toJson(new Response(1,true,"人脸识别失败"));
    }
    @RequestMapping("/deleteFace")
    public String deleteFace(@RequestBody User user){
        String result=faceService.deleteFace(user.getId());
        if(result==null){
            return gson.toJson(new Response(1,true,"删除失败"));
        }
        return result;
    }

}
