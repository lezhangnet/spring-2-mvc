package net.lezhang.spring.mvcbasic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.lezhang.spring.mvcdao.Offer;
import net.lezhang.spring.mvcservice.OfferService;

@Controller
public class OffersController {

    @Autowired
    private OfferService offerService;

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

    // using Model as argument is more elegant than ModelAndView above
    @RequestMapping("/offers")
    public String showOffers(Model model) {
        model.addAttribute("testModelKey", "<b>newTestModelValue</b>");

        List<Offer> offers = offerService.getCurrentOffers();
        model.addAttribute("offers", offers);

        return "modelandview";
    }

    @RequestMapping("/createoffer")
    public String createOffer() {

        return "createoffer";
    }

}
