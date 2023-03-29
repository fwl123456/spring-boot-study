package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// @WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ABC拦截到了请求...放行前逻辑");
        //放行
        filterChain.doFilter(request, response);

        System.out.println("ABC拦截到了请求...放行后逻辑");
    }
}
