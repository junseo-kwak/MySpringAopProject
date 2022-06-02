package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable{

        log.info("[Retry] {}, retry ={}", joinPoint.getSignature(),retry);

        int maxRetryCount = retry.value();
        Exception exceptionHolder = null;
        for(int tryCount = 1; tryCount <= maxRetryCount; tryCount++){
            try{
                log.info("tryCount / maxRetryCount = {} / {}", tryCount,maxRetryCount);
                return joinPoint.proceed();
            }catch(Exception e){
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;

    }




}
