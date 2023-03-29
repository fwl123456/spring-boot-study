package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j // 打印日志
@Component // 交给IOC容器管理
@Aspect // 表示是切面类
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    // 要调用mapper里面的insert方法完成对日志的新增 这里自动注入operateLogMapper对象
    @Autowired
    private OperateLogMapper operateLogMapper;

    // 环绕通知 通过注解方式匹配切入点表达式
    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人id -> 当前登录员工ID
        // 获取请求头中的JWT令牌 解析令牌
        String jwt = request.getHeader("token");

        // 调用JwtUtils工具类中的解析方法parseJWT
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法名
        String methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 原始方法运行前记录时间
        long begin = System.currentTimeMillis();

        //调用原始目标方法运行
        Object result = joinPoint.proceed();

        // 原始方法运行后记录时间
        long end = System.currentTimeMillis();

        // 方法返回值
        // 使用阿里巴巴fastjson 提供的JSONObject.toJSONString方法一个对象转换为json格式
        String returnValue = JSONObject.toJSONString(result);

        // 操作耗时
        long costTime = end - begin;

        // 记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志: {}", operateLog);
        return result;

    }
}
