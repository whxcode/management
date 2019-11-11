package com.cn.scitc.security;

import com.cn.scitc.entity.SysUser;
import com.cn.scitc.repository.SysUserRepository;
import com.cn.scitc.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义登录验证
 * @author jswzj
 */
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private SysUserRepository userRepository;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         //获取表单输入的用户名 和密码
        String usernmae =  authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        SysUser user = userRepository.findByUsername(usernmae);
        if (user == null){
            throw new AuthenticationCredentialsNotFoundException("用户名" + usernmae + "不存在");
        }

        //判断用户输入的密码是否和加密的密码结果一样
        if(!user.getPassword().equals(MD5Util.encode(inputPassword))){

            throw new BadCredentialsException("用户密码错误!");

        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(user, inputPassword, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
