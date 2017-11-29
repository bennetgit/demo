package spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.demo.annotation.WebLog;

/**
 * Created by facheng on 17.03.17.
 */
@Controller
@WebLog
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

}
