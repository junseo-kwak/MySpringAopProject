package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success(){
        memberService.hello("helloA");
    }




    @Aspect
    @Slf4j
    static class ParameterAspect{

        @Pointcut("execution(* hello.aop.member..*.*(..))")
        private void allMember(){}

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg = joinPoint.getArgs()[0];
            log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(),arg);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint,Object arg) throws Throwable {
            log.info("[logArgs2]{}, arg={}", joinPoint.getSignature(),arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg){
            log.info("[logArgs3] arg={}", arg);
        }

        @Before("allMember() && this(obj))")
        public void thisArg(JoinPoint joinPoint, Object obj){
            log.info("[this]{}, obj= {}", joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && target(obj))")
        public void targetArg(JoinPoint joinPoint, Object obj){
            log.info("[target]{}, obj= {}", joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && @target(annotation))")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation){
            log.info("[atTarget]{}, annotation= {}", joinPoint.getSignature(),annotation);
        }

        @Before("allMember() && @within(annotation))")
        public void atWithin(JoinPoint joinPoint, ClassAop annotation){
            log.info("[atWithin]{}, annotation= {}", joinPoint.getSignature(),annotation);
        }

        @Before("allMember() && @annotation(annotation))")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation){
            log.info("[atAnnotation]{}, annotation= {}", joinPoint.getSignature(),annotation.value());
        }
    }


}
