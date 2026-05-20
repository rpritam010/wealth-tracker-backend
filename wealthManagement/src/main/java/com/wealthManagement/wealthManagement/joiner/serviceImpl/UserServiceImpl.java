package com.wealthManagement.wealthManagement.joiner.serviceImpl;

import com.wealthManagement.wealthManagement.joiner.service.UserService;
import com.wealthManagement.wealthManagement.mybatis.mapper.UserMapper;
import com.wealthManagement.wealthManagement.pojo.responseBody.UserDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDetailMapper> getAllUsers() {
        return userMapper.selectOne();
    }
}
