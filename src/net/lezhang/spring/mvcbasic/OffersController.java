package net.lezhang.spring.mvcbasic;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OffersController {

    @RequestMapping("/")
    public String home() {
        return "home"; // to generate view name via view resolver defined in sevlet
    }

    @RequestMapping("/session")
    public String session(HttpSession session) {
        session.setAttribute("testAttribute", "testAttributeValue");
        return "session";
    }

    @RequestMapping("/modelandview")
    public ModelAndView modelandview() {
        ModelAndView mv = new ModelAndView("modelandview"); // view (jsp) name here
        Map<String, Object> model = mv.getModel();
        model.put("testModelKey", "testModelValue"); // will NOT persist in session
        return mv;
    }

    // more elegant than ModelAndView above
    @RequestMapping("/model")
    public String model(Model model) {
        model.addAttribute("testModelKey", "<b>newTestModelValue</b>");
        return "modelandview";
    }

}
