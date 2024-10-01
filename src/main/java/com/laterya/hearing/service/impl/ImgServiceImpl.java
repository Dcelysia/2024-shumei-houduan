package com.laterya.hearing.service.impl;

import com.google.gson.Gson;
import com.laterya.hearing.mapper.ImgMapper;
import com.laterya.hearing.service.ImgService;
import com.laterya.hearing.util.FileUploadUtils;
import com.laterya.hearing.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class ImgServiceImpl implements ImgService {

    private Gson gson=new Gson();
    @Autowired
    private ImgMapper imgMapper;
    @Override
    public String updateImg(MultipartFile file, String baseDir, int id) {
        String originalFilename=file.getOriginalFilename();
        if(originalFilename==null||"".equals(originalFilename)){
            return gson.toJson(new Response(1,true,"上传文件不能为控"));
        }
        String fileLocation=null;
        try {
            fileLocation= FileUploadUtils.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(imgMapper.getImg(id)!=null){
            imgMapper.updateImg(id,fileLocation);
        }
        else{
            imgMapper.insertImg(id,fileLocation);
        }
        return gson.toJson(new Response(0,false,null,fileLocation));
    }

    @Override
    public String getImg(int id) {
        return gson.toJson(new Response(0,false,null,imgMapper.getImg(id)));
    }
}
