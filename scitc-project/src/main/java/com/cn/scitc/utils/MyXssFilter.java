package com.cn.scitc.utils;

import  javax.servlet.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 *
 */
@WebFilter(filterName="myXssFilter", urlPatterns="/*")
public class MyXssFilter implements Filter {

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行过滤操作");

        filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
        this.filterConfig = null;
    }

}
