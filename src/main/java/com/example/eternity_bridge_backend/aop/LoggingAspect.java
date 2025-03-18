package com.example.eternity_bridge_backend.aop;

import com.example.eternity_bridge_backend.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController(){}


    @Before("restController()")
    public void beforeAPI(JoinPoint joinPoint)  {
        String apiName = joinPoint.getSignature().getName();
        String trxKey = LogUtils.generateTrxKey();

        // MDC: 스레드-세이프한 로깅 컨텍스트
        MDC.put("trxKey", trxKey);
        log.info("[{}] ========== {} START ==========", trxKey, apiName);
    }


    @After("restController()")
    public void AfterAPI(JoinPoint joinPoint) {
        String apiName = joinPoint.getSignature().getName();
        String trxKey = MDC.get("trxKey");

        log.info("[{}] ========== {} END ==========", trxKey, apiName);
        log.info("");

        MDC.clear();
    }

}
