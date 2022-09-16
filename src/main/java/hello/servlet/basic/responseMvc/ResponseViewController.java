package hello.servlet.basic.responseMvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");

        return mav;
    }

    //@ResponseBody를 쓰게 되면 view로 안가고 문자로 가게된다!!
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    //view 이름을 없애고 경로에 이름을 맞춰 줫는데 너무 생략해서 권하지는 않는다..
    //void 일 떄, 조건 url이 같고 http 바디 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용. 스프링이 알아서 -> 그치만 노노권장 v2정도가 적당
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
