package com.laterya.hearing.controller;

import com.google.gson.Gson;
import com.laterya.hearing.model.pojo.Data;
import com.laterya.hearing.service.DataService;
import com.laterya.hearing.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/data")
public class DataController {
    private Gson gson=new Gson();
    @Autowired
    private DataService dataService;

    @RequestMapping("/addData")
    public String addData(@RequestBody Data data){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        data=new Data(sdf.format(new Date()),data.getMileage(),data.getType(),data.getId(),data.getLocation());
        dataService.addData(data);
        return gson.toJson(new Response(0,false,null));
    }

    @RequestMapping("/selectById")
    public String selectById(@RequestBody Data data) {
        List<Data> list=dataService.selectById(data.getId());
        return gson.toJson(new Response(0,false,null,list));
    }

    @RequestMapping("/selectAllById")
    public String selectAllById(@RequestBody Data data)  {
        Set set=dataService.selectAllById(data.getId());
        return gson.toJson(new Response(0,false,null,set));
    }

}
