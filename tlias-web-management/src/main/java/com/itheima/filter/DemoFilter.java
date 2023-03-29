package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// 配置过滤器要拦截的请求 /*表示所有请求
// @WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {

    @Override // 初始化方法 只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 方法执行了");
    }

    @Override // 拦截到请求之后调用, 调用多次
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("demo拦截到了请求...放行前逻辑");
        //放行
        filterChain.doFilter(request, response);

        System.out.println("拦截到了请求...放行后逻辑");
    }

    @Override// 销毁方法 只调用一次
    public void destroy() {
        System.out.println("destroy 方法执行了");
    }
}
