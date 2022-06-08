package hello.aop.proxyvs;


import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false);
        
        // 프록시 -> 타겟의 인터페이스로 캐스팅 가능
        MemberService proxy = (MemberService) factory.getProxy();
        // 프록시 -> 타겟의 구체클래스로 캐스팅 불가능
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl memberServiceImpl = (MemberServiceImpl) proxy;
        });
    }

    @Test
    void cglibTest(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true);

        // 프록시 -> 타겟의 인터페이스로 캐스팅 가능
        MemberService proxy = (MemberService) factory.getProxy();

        log.info("proxy= {}", proxy.getClass());

        // 프록시 -> 타겟의 구체클래스로 캐스팅 가능
        MemberServiceImpl castingClass = (MemberServiceImpl)proxy;
    }
}
