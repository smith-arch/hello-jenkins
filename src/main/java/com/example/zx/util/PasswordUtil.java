package com.example.zx.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    /**
     * 加密密码（注册/修改密码时调用）
     */
    public static String encrypt(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    /**
     * 验证密码（登录时调用）
     */
    public static boolean verify(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
