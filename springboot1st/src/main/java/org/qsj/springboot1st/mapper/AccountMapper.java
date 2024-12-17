package org.qsj.springboot1st.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.qsj.springboot1st.entity.Account;

@Mapper
public interface AccountMapper {


    @Select("select * from users where username=#{username}")
    Account getUserName(@Param("username") String username);
}

