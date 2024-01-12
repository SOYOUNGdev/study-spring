package com.example.ex02.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TimeMapper {
//    @Select("SELECT SYSDATE FROM DUAL")
    public String getTime();
}
