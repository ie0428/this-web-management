package com.itheima.t.Filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.t.pojo.Result;
import com.itheima.t.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        //前置：强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求URL
        String url=request.getRequestURL().toString();
        log.info("请求的URL是：{}",url); //请求路径：http://localhost:8080/login

        //2.判断请求URL中是否包含，如果包含，说明是登录操作，放行
        if(url.contains("/login")){
            chain.doFilter(request,response);//放行请求
            return;
        }

        //3.获取请求头中的令牌token
        String token=request.getHeader("token");
        log.info("请求头中的令牌是：{}",token);

        //4.判断token是否为空，如果为空，说明未登录，拦截，返回未登录结果
        if (!StringUtils.hasLength(token)){
            log.info("token不存在");

            Result responseRusult=Result.error("NOT_LOGIN");
             //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json= JSONObject.toJSONString(responseRusult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("解析令牌失败");

            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);

            return;
        }

        //6.放行
        chain.doFilter(request,response);
    }
}
