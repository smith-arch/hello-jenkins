package com.example.zx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.zx.entity.BillDetail;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface BillDetailMapper extends BaseMapper<BillDetail> {

    BigDecimal sumAmountByRange(LocalDateTime startTime, LocalDateTime endTime);
}
