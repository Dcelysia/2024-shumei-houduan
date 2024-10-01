package com.laterya.hearing.service.impl;

import com.laterya.hearing.mapper.DataMapper;
import com.laterya.hearing.model.pojo.Data;
import com.laterya.hearing.service.DataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;
    @Override
    public void addData(Data data) {

        dataMapper.addData(data);
    }
    @Override
    public List<Data> selectById(int id) {
        List<Data> list=dataMapper.selectById(id);
        return list;
    }

    @Override
    public Set selectAllById(int id)  {
        List<Data> list=dataMapper.selectAllById(id);
        Set set=new HashSet();
        int size=list.size()-1;
        long mile = 0;
        /*
        long second=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if(size>=1){
            Date d1=sdf.parse(list.get(size).getTime());
            Date d2=sdf.parse(list.get(0).getTime());
            second=(d1.getTime()-d2.getTime())/1000;
        }
         */
        while (size>=0){
            mile+=Integer.parseInt(list.get(size).getMileage());
            size-=1;
        }

        System.out.println(set);
        if(list.size()-1>=0){
            set.add("id:"+list.get(0).getId());
            set.add("username:"+list.get(0).getUsername());
            set.add("mileage:"+mile);
        }
        else {
            set.add("未找到该用户信息");
            return set;
        }
        return set;
    }

    /*@Override
    public List<Data> loginById(int id) {
        List<Data> list=dataMapper.loginById(id);
        return list;
    }*/


}
