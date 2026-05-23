package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.HoldingsSnapshotDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HoldingsSnapshotMapper {

    /** Insert or replace same-day snapshot for same portfolio+asset */
    void upsert(HoldingsSnapshotDto dto);
}