package cn.marssky.account.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {

    @Around("accountOps()")
    public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        Object obj = null;
        try {
            name = pjp.getSignature().toShortString();
            obj = pjp.proceed();
        }
        catch(Throwable e) {
            result="N";
            throw e;
        }
        finally {
            long endTime = System.currentTimeMillis();
            log.info("{};{};{}ms",name,result,endTime-startTime);
        }
        return obj;
    }

    @Pointcut("execution(* net.marssky.account.service..*(..))")
    private void accountOps(){};

}