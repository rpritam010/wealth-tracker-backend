package com.wealthManagement.wealthManagement.pojo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private Long id;

    private String fullName;

    private String email;

    private String mobileNumber;

    private String pan;
}