package com.example.zx.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账单详情表
 */
@Data
@TableName("bill_detail")
public class BillDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 金额（元）
     */
    private BigDecimal amount;

    /**
     * 类别ID
     */
    private Long categoryId;

    /**
     * 支付工具：1-微信支付 2-支付宝 3-现金 4-银行卡 5-云闪付 6-其他
     */
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
    private Integer channel;

    /**
     * 消费意图，如 解馋、刚需、囤货
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
     * 状态（预留字段）
     */
    private Integer status;

    /**
     * 实际消费时间
     */
    private LocalDateTime consumeTime;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
}
