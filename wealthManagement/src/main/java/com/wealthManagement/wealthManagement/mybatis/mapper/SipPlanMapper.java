package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.SipPlanDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SipPlanMapper {

    void insertSipPlan(SipPlanDto dto);
}