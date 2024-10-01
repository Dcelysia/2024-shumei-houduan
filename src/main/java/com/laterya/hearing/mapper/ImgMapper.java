package com.laterya.hearing.mapper;

import org.apache.ibatis.annotations.Param;

public interface ImgMapper {
    void updateImg(@Param("id") int id, @Param("imgLocation") String imgLocation);
    String getImg(@Param("id") int id);
    void insertImg(@Param("id") int id,@Param("imgLocation") String imgLocation);
}
