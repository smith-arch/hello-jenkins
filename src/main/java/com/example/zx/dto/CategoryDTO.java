package com.example.zx.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 类别 DTO - 用于新增/修改请求
 */
@Data
public class CategoryDTO {

    /**
     * 主键（修改时传入）
     */
    private Long id;

    /**
     * 类别编码
     */
    @NotBlank(message = "类别编码不能为空")
    private String code;

    /**
     * 类别中文名
     */
    @NotBlank(message = "类别名称不能为空")
    private String name;

    /**
     * 类别描述
     */
    private String description;
}
