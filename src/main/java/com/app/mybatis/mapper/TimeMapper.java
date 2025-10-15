package com.app.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper                             // mapper.xml에 알려주기 위한 어노테이션
public interface TimeMapper {
    public String getTime();

//    @Select("SELECT CURRENT_TIMESTAMP FROM DUAL")


}
