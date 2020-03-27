package cn.marssky.video.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    @Around("accountOps()")
    public Object logPerformance(ProceedingJoinPoint point) throws  Throwable{
        //定义开始时间
        long startTime=System.currentTimeMillis();
        String name="-";
        String result="Y";
        Object obj=null;
        //获取方法的签名 和名称
        log.debug("执行前：");
        Map<String, Object> params = getNameAndValue(point);
        String methodName = point.getSignature().getName();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            log.info("方法名"+methodName+" 参数：name: " + entry.getKey() + " value: " + entry.getValue());
        }
      try {
          name= point.getSignature().toShortString();
          obj=point.proceed();
          log.debug("执行后：");
          log.info("返回"+obj);
      }catch (Throwable e){
          result="N";
          throw e;
      }finally {
          long endTime=System.currentTimeMillis();
          log.info("{};{};{}ms",name,result,endTime-startTime);
      }
        return obj;
    }

    @Pointcut("execution(* cn.marssky.video.service..*(..))")
    private void accountOps(){};

    /**
     * 获取参数Map集合
     * @param joinPoint
     * @return
     */
    Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();

        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }

        return param;
    }
}
