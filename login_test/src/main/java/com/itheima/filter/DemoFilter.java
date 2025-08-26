package com.itheima.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
// web服务器启动的时候执行，只执行一次
        System.out.println("init 初始化方法执行了。。。");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

//    销毁方法，web服务器关闭的时候执行，只执行一次
    @Override
    public void destroy() {
        System.out.println("destroy 销毁方法执行了...");
    }
}
