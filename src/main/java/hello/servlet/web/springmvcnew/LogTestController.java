package hello.servlet.web.springmvcnew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController // 반환하는 타입이 그대로 반환됨 Controller이라고 하면 View이름이 반환되서 뷰 리졸버가 찾고 그럼.
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);
        log.trace(" trace log={}", name); //메서드 호출 파라미터 넘기고 끝남 ㅎㅎ
//        log.trace(" trace log="+ name);
        // 이렇게 하면 name에 값을 먼저 넣고 등등의 연산이 일어남. 메모리 시피유가 사용이 되겟지
        // 이게 문제가 되는게 로그 레벨이 info인데도 저런식으로+쓰면 trace레벨에서도 연산이 일어남 쓰지도 않는데
        // -> 문자 +가 오면서 쓸데 없는 연산이 이루어짐
        log.debug(" debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error(" info log={}", name);
        //log level 지정 가능

        return "ok";
    }
}

