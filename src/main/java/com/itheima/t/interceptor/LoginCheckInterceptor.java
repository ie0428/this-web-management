package com.itheima.t.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.t.pojo.Result;
import com.itheima.t.utils.JwtUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//**自定义拦截器：**实现HandlerInterceptor接口，并重写其所有方法
@Component //当前拦截器对象由Spring创建和管理
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    //目标资源方法执行前执行。 返回true：放行    返回false：不放行
    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        System.out.println("preHandle...");


        //1.获取请求URL
        String url=request.getRequestURL().toString();
        log.info("请求的URL是：{}",url); //请求路径：http://localhost:8080/login

        //2.判断请求URL中是否包含，如果包含，说明是登录操作，放行
        if(url.contains("/login")){
            log.info("登录操作，放行");//放行请求
            return true;
        }

        //3.获取请求头中的令牌token
        String token=request.getHeader("token");
        log.info("请求头中的令牌是：{}",token);

        //4.判断token是否为空，如果为空，说明未登录，拦截，返回未登录结果
        if (!StringUtils.hasLength(token)){
            log.info("token不存在");

            //创建响应结果对象
            Result responseRusult= Result.error("NOT_LOGIN");
             //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json= JSONObject.toJSONString(responseRusult);
            //设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return false;//不放行
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            log.info("解析令牌失败");

            //创建响应结果对象
            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            //设置响应头
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);

            return false;
        }

        //6.放行
        return true;
    }

    //目标资源方法后台执行
    @Override
    public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

     //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion .... ");
    }
}
