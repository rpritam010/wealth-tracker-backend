package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.UserDto;
import com.wealthManagement.wealthManagement.pojo.responseBody.UserDetailMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDetailMapper> selectOne();

    void insertUser(UserDto userDto);

    UserDto selectUserByPan(@Param("pan") String pan);

    /** Fallback lookup when PAN is null in the CAS */
    UserDto selectUserByEmail(@Param("email") String email);
}