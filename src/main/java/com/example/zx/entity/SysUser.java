package com.example.zx.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    // 用户名
    @TableField("username")
    private String username;

    // 密码
    @TableField("password")
    private String password;

    // 状态（0正常 1禁用）
    @TableField("status")
    private Integer status;

    // 最后登录ip
    @TableField("last_login_ip")
    private String lastLoginIp;

    // 最后登录时间
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    // 创建时间
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 更新时间
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // 逻辑删除字段
    @TableLogic
    @TableField("deleted")
    private Integer deleted;
}
