package com.example.zx.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账单详情 DTO - 用于新增/修改请求
 */
@Data
public class BillDetailDTO {

    /**
     * 主键（修改时传入）
     */
    private Long id;

    /**
     * 归属账单ID
     */
    @NotNull(message = "账单ID不能为空")
    private Long billId;

    /**
     * 消费项目
     */
    @NotBlank(message = "消费项目不能为空")
    private String itemName;

    /**
     * 项目描述
     */
    private String itemDesc;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0.01", message = "金额必须大于0")
    private BigDecimal amount;

    /**
     * 类别ID
     */
    @NotNull(message = "类别不能为空")
    private Long categoryId;

    /**
     * 支付工具：1-微信支付 2-支付宝 3-现金 4-银行卡 5-云闪付 6-其他
     */
    @NotNull(message = "支付工具不能为空")
    private Integer paymentMethod;

    /**
     * 银行卡种类：1-储蓄卡 2-信用卡
     */
    private Integer cardType;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 消费途径：1-线上 2-线下
     */
    @NotNull(message = "消费途径不能为空")
    private Integer channel;

    /**
     * 消费意图
     */
    private String consumeIntent;

    /**
     * 消费理念：1-理性消费 2-非理性消费 3-正常消费 4-异常消费 5-冲动消费
     */
    private Integer consumeType;

    /**
     * 是否纳入统计：0-不统计 1-统计
     */
    private Integer isCounted;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 实际消费时间
     */
    @NotNull(message = "消费时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consumeTime;
}
