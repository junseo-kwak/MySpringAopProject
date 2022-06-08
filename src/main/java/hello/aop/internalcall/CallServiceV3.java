package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalService internalService;


    public void external(){
        log.info("external() call");
        internalService.internal();
    }



}