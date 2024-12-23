package com.itheima.t.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect//当前类为切面类
@Slf4j
public class TimeAspect {

    @Around("execution(* com.itheima.t.controller.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable{
        //记录方法执行开始的时间
        long begin=System.currentTimeMillis();

        //执行原始方法
        Object result=pjp.proceed();

        //记录方法执行结束的时间
        long end=System.currentTimeMillis();

        //计算方法执行耗时
        log.info("{}执行结束，耗时{}毫秒",pjp.getSignature().getName(),(end-begin));
        return result;
    }
}
