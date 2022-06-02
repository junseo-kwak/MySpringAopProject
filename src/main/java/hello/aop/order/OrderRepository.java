package hello.aop.order;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {


    public String save(String itemId){

        log.info("[OrderRepository] 호출 ");
        if(itemId.equals("ex")){
            throw new IllegalStateException();
        }
        return "ok";
    }


}
