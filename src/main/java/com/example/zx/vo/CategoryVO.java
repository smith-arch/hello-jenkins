package com.example.zx.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类别 VO - 用于返回给前端
 */
@Data
public class CategoryVO {

    private Long id;

    /**
     * 类别编码
     */
    private String code;

    /**
     * 类别中文名
     */
    private String name;

    /**
     * 类别描述
     */
    private String description;

    private LocalDateTime createTime;
}
