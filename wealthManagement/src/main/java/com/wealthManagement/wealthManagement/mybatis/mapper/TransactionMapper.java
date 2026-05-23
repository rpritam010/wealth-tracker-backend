package com.wealthManagement.wealthManagement.mybatis.mapper;

import com.wealthManagement.wealthManagement.pojo.dto.TransactionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TransactionMapper {

    void insertTransaction(TransactionDto transactionDto);

    Integer checkTransactionExists(
            @Param("folioNumber") String folioNumber,
            @Param("transactionDate") String transactionDate,
            @Param("transactionType") String transactionType,
            @Param("totalAmount") BigDecimal totalAmount,
            @Param("quantity") BigDecimal quantity
    );
    List<TransactionDto> selectTransactionsByFolio(
            @Param("folioNumber") String folioNumber
    );

}