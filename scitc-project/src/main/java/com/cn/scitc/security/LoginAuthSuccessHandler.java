package com.cn.scitc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 验证成功处理器
 * @author jswzj
 *
 */
@Component("applicationAuthenticationSuccessHandler")
public class LoginAuthSuccessHandler implements AuthenticationSuccessHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.info("登录成功!");
		response.sendRedirect("/");

		
	}

}
