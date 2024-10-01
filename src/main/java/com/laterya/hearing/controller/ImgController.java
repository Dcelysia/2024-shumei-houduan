package com.laterya.hearing.controller;

import com.laterya.hearing.model.pojo.User;
import com.laterya.hearing.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/img")
public class ImgController {
    @Autowired
    private ImgService imgService;
    @RequestMapping("/updateImg")
    public String updateImg(MultipartFile file, int id){
        return imgService.updateImg(file,null,id);
    }
    @RequestMapping("/getImg")
    public String getImg(@RequestBody User user){
        System.out.println(user.getId());
        return imgService.getImg(user.getId().intValue());
    }
}