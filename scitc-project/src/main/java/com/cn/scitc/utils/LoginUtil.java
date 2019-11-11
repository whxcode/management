package com.cn.scitc.utils;

import com.cn.scitc.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

public   class LoginUtil {
    /**
     * 加载当前登录用户 id
     * @return
     */
    public static SysUser load() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null && principal instanceof SysUser) {
            return (SysUser) principal;
        }
        return null;
    }


    public static Long getLoginUserId() {
        SysUser user = load();
        if (user == null) {
            return -1L;
        }

        return user.getId();
    }
}
