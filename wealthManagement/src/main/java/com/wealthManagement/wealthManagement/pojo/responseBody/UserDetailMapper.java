package com.wealthManagement.wealthManagement.pojo.responseBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserDetailMapper {
    private String userId;
    private String name;
    private  String email;
    private String mobileNumber;
    private String createdAt;
    private  String updatedAt;
}
