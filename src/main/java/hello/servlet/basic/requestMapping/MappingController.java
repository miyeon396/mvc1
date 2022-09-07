package hello.servlet.basic.requestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-go"}) //url이 오면 이 메서드가 호출 된다.
    public String helloBasicAllMethod() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET) //url이 오면 이 메서드가 호출 된다.
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2") //막상 GetMapping 들어가보면 V1처럼 되어있음 ㅋㅋ
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable 사용 -> 진짜 마니 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping(value = "/mapping/{userId}")
//    public String mappingPath(@PathVariable("userId") String data) { //아래랑 같은 의미
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping(value = "/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑 -> 잘 사용하지는 않음
     * params = "mode",
     * params = "!mode",
     * params = "mode=debug"
     * params = "mode!=debug"
     * params = {"mode=debug", "data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }
    //Param에 mode=debug란게 있어야 매핑이 되는 것.

    /**
     * 특정 헤더로 추가 매핑
     * headers = "mode",
     * headers = "!mode",
     * headers = "mode=debug"
     * headers = "mode!=debug"
     */
    @GetMapping(value = "/mapping-header", params = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }
}
