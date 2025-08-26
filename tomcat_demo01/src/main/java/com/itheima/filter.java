package com.itheima;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FilterDemo1");
//        放行
        chain.doFilter(request,response);
//        放行后逻辑
        System.out.println("3. FilterDemo");
    }

    @Override
    public void destroy() {

    }
}
