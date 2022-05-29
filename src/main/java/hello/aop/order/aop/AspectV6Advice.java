package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

//    @Around("hello.aop.order.aop.PointCuts.allOrders()")
//    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("[LOG] {}", joinPoint.getSignature());
//
//        return joinPoint.proceed();
//    }
//
//
//    @Around("hello.aop.order.aop.PointCuts.orderAndService()")
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
//        try{
//            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
//            return result;
//        }catch(Exception e){
//            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
//            throw e;
//        }finally{
//            log.info("[리소스 반환] {}", joinPoint.getSignature());
//        }
//    }


    @Before("hello.aop.order.aop.PointCuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.PointCuts.orderAndService()", returning = "result")
    public void doAfter(JoinPoint joinPoint,Object result){
        log.info("[Return] {}, return = {}",joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.PointCuts.orderAndService()",throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex){
        log.info("[Ex] {}, message={}",joinPoint.getSignature(),ex);
    }

    @After(value = "hello.aop.order.aop.PointCuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint){
        log.info("[After] {}",joinPoint.getSignature());
    }


}
