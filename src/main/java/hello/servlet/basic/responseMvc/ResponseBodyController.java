package hello.servlet.basic.responseMvc;

import hello.servlet.basic.HelloDataMvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@ResponseBody //메서드마다 붙이지 않아도 클래스 레벨에서 붙여도 됨.
// 요거 두개 합친게 @RestController = @Controller + @ResponseBody
public class ResponseBodyController {

    //1~3 문자를 처리할 때 response 점점 편하게 개선 예제
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    //json을 처리할 때
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloDataMvc> responseBodyJsonV1() {
        HelloDataMvc helloData = new HelloDataMvc();
        helloData.setUsername("useA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK) //응답 상태코드를 못바꾸니 위에서 지정
    @ResponseBody // 사용 시 뷰를 사용하지 않고 HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접 입력할 수 있다.
    @GetMapping("/response-body-json-v2")
    public HelloDataMvc responseBodyJsonV2() {
        HelloDataMvc helloData = new HelloDataMvc();
        helloData.setUsername("useA");
        helloData.setAge(20);
        return helloData;
    }
}
