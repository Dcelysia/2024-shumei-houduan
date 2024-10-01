package com.laterya.hearing.service;

import com.laterya.hearing.model.pojo.Data;
import java.util.List;
import java.util.Set;

public interface DataService {
    void addData(Data data);
    List<Data> selectById( int id) ;
    Set selectAllById(int id) ;
    /*List<Data> loginById(@Param("id") int id) ;*/
}
