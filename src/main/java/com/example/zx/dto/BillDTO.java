package com.example.zx.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 账单 DTO - 用于新增/修改请求
 */
@Data
public class BillDTO {

    /**
     * 主键（修改时传入）
     */
    private Long id;

    /**
     * 主题
     */
    @NotBlank(message = "账单主题不能为空")
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 账单日期
     */
    @NotNull(message = "账单日期不能为空")
    private LocalDate billDate;
}
