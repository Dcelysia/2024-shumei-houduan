package com.laterya.hearing.mapper;

import com.laterya.hearing.model.pojo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataMapper {
    void addData(@Param("data") Data data);
    List<Data> selectById(@Param("id") int id) ;
    List<Data> selectAllById(@Param("id") int id) ;

}
