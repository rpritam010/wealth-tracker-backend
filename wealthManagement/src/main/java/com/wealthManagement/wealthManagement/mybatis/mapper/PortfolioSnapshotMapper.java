package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.PortfolioSnapshotDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PortfolioSnapshotMapper {

    /** Insert or replace same-day snapshot for same portfolio */
    void upsert(PortfolioSnapshotDto dto);
}