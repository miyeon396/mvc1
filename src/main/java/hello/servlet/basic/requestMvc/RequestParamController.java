package hello.servlet.basic.requestMvc;

import hello.servlet.basic.HelloDataMvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //ok라는 문자를 http 응답메시지에 넣어서 반환한다. -> restController annotation과 동일한 결과
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    //생략 가능 예제
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //인간의 욕심은 끝이 없죠. 완전 간단하죠
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        // null 과 "" 구분
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age) {
        //defaultValue는 빈문자 일때도 적용이 된다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
//        HelloDataMvc helloDataMvc = new HelloDataMvc();
//        helloDataMvc.setUsername(username);
//        helloDataMvc.setAge(age);
//
//        log.info("username={}, age={}", helloDataMvc.getUsername(), helloDataMvc.getAge());
//        log.info("helloData={}", helloDataMvc);
//
//        return "ok";
//    }

    //위의 주석 예제 간소화
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloDataMvc helloDataMvc) {
        log.info("username={}, age={}", helloDataMvc.getUsername(), helloDataMvc.getAge());
        log.info("helloData={}", helloDataMvc);

        return "ok";
    }

    //@ModelAttribute 생략 가능
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloDataMvc helloDataMvc) {
        log.info("username={}, age={}", helloDataMvc.getUsername(), helloDataMvc.getAge());
        log.info("helloData={}", helloDataMvc);

        return "ok";
    }
}
