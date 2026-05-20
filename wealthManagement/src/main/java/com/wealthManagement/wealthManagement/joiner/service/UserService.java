package com.wealthManagement.wealthManagement.joiner.service;

import com.wealthManagement.wealthManagement.pojo.responseBody.UserDetailMapper;

import java.util.List;

public interface UserService {
    List<UserDetailMapper> getAllUsers();
}
