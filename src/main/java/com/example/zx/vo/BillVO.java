package com.example.zx.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 账单 VO - 用于返回给前端
 */
@Data
public class BillVO {

    private Long id;

    /**
     * 主题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 账单日期
     */
    private LocalDate billDate;

    /**
     * 消费总额
     */
    private BigDecimal totalAmount;

    /**
     * 明细条数
     */
    private Integer detailCount;

    /**
     * 账单下的消费明细列表（按需填充）
     */
    private List<BillDetailVO> details;

    private LocalDateTime createTime;

    private String createBy;
}