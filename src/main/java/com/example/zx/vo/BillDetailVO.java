package com.example.zx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账单详情 VO - 用于返回给前端
 */
@Data
public class BillDetailVO {

    private Long id;

    /**
     * 归属账单ID
     */
    private Long billId;

    /**
     * 消费项目
     */
    private String itemName;

    /**
     * 项目描述
     */
    private String itemDesc;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 类别ID
     */
    private Long categoryId;

    /**
     * 类别名称（关联查询填充，前端直接展示）
     */
    private String categoryName;

    /**
     * 支付工具：1-微信支付 2-支付宝 3-现金 4-银行卡 5-云闪付 6-其他
     */
    private Integer paymentMethod;

    /**
     * 支付工具中文（转换后填充）
     */
    private String paymentMethodText;

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
    private Integer channel;

    /**
     * 消费途径中文
     */
    private String channelText;

    /**
     * 消费意图
     */
    private String consumeIntent;

    /**
     * 消费理念：1-理性 2-非理性 3-正常 4-异常 5-冲动
     */
    private Integer consumeType;

    /**
     * 消费理念中文
     */
    private String consumeTypeText;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consumeTime;

    /**
     * 记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;
}
