package hello.aop.exam;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ExamRepository {
    
    private static int seq = 0;
    @Trace
    @Retry(value = 4)
    public String save(String itemId){
        seq++;

        if(seq == 5){
            throw new IllegalArgumentException("예외 발생");
        }
        
        return "ok";
    }
    
    
}
