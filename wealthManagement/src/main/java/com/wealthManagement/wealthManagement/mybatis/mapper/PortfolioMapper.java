package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.PortfolioDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PortfolioMapper {

    void insertPortfolio(PortfolioDto portfolioDto);

    PortfolioDto selectPortfolioByUserId(
            @Param("userId") Long userId
    );
}