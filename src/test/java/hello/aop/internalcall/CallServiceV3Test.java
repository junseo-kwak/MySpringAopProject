package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import(CallLogAspect.class)

public class CallServiceV3Test {

    @Autowired
    private CallServiceV3 callService;

    @Test
    void external(){
        callService.external();
    }
}
