package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //스프링이 자동으로 빈으로 등록 + RequestMappingHandlerMapping에서 RequestMapping 인식
//@Component + @RequestMapping을 @Controller 대신 써줘도 똑같이 스프링 빈으로 들어감
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form"); //viewResolver에서 jsp를 처리하기 위한게 찾아서 매핑이됨?
    }

}
