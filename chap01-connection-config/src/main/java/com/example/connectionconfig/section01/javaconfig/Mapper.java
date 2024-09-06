package com.example.connectionconfig.section01.javaconfig;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface Mapper {
    @Select("SELECT NOW()")
    java.util.Date selectDate();
}
