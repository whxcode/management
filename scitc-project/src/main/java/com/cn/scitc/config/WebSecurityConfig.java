package com.cn.scitc.config;

import com.cn.scitc.filter.KaptchaAuthenticationFilter;
import com.cn.scitc.security.AuthProvider;
import com.cn.scitc.security.LoginUrlEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * webmvc
 * @author jswzj
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationSuccessHandler applicationAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		http    .
				addFilterBefore(new KaptchaAuthenticationFilter("/login", "/login?error"),
				UsernamePasswordAuthenticationFilter.class)

				.authorizeRequests()
    	                .antMatchers("/static/**").permitAll()
						.antMatchers("/doc.html").hasAnyRole("ROLE_ADMIN")
						.antMatchers("/login").permitAll()
				        .antMatchers("/UserCountDownloads").hasAnyRole("ROLE_ADMIN")
				        .antMatchers("/getKaptchaImage").permitAll()
				        .anyRequest().authenticated()//所有的请求需要认证即登陆后才能访问
                        .and()
                        .formLogin()
				        .loginProcessingUrl("/login") // 配置角色登录处理入口

						.successHandler(applicationAuthenticationSuccessHandler)

						 .and()

						.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/logout/page")
						.deleteCookies("JSESSIONID")
						.invalidateHttpSession(true)
						.and()
						.exceptionHandling()
				         .authenticationEntryPoint(urlEntryPoint())
						.accessDeniedPage("/403");

					http.csrf().disable();
					http.headers().frameOptions().sameOrigin();
		            //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
		            http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    	    }

		@Autowired
		public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authProvider()).eraseCredentials(true);
		}

		@Bean
		public AuthProvider authProvider() {
			return new AuthProvider();
		}

	    @Bean
        public LoginUrlEntryPoint urlEntryPoint () {
            return new LoginUrlEntryPoint("/login");
        }

		@Bean
		public AuthenticationManager authenticationManager() {
			AuthenticationManager authenticationManager = null;
			try {
				authenticationManager =  super.authenticationManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return authenticationManager;
		}


}
