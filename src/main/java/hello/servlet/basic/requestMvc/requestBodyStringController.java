package hello.servlet.basic.requestMvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class requestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");

    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer resopnseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        resopnseWriter.write("ok");

    }

    //Stream 받는거 시러
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException { //http 메세지 컨버터가 작동을 함

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");

    }

    //위에서 HttpEntity -> RequestEntity , ResponseEntity로 변경 가능 -> 결국 둘다 HttpEntity를 상속 받은 것.
    @PostMapping("/request-body-string-v3-example")
    public HttpEntity<String> requestBodyStringV3Example(RequestEntity<String> httpEntity) throws IOException { //http 메세지 컨버터가 작동을 함

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);

    }

    //애노테이션을 써야해? 귀차나 -> requestBody
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(@RequestBody String messageBody) { //http body 읽어서 여따 딱 넣어주는 것

        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");

    }
}
