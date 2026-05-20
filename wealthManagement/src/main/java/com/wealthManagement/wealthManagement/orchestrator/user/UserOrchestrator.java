package com.wealthManagement.wealthManagement.orchestrator.user;

import com.wealthManagement.wealthManagement.joiner.service.UserService;
import com.wealthManagement.wealthManagement.orchestrator.base.BaseOrchestrator;
import com.wealthManagement.wealthManagement.pojo.responseBody.UserDetailMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserOrchestrator extends BaseOrchestrator {

    private final UserService userService;

    public UserOrchestrator(UserService userService) {
        this.userService = userService;
    }

    public List<UserDetailMapper> getAllUsers() {

        logRequest("GET_USERS");
        return userService.getAllUsers();
    }
}
