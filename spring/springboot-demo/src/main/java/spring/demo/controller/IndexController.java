package spring.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.demo.dto.response.ResponseInfo;
import spring.demo.security.entity.AuthUser;

/**
 * Created by facheng on 10.03.17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("index/init")
    @ResponseBody
    public ResponseInfo initMenuList() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser == null) {
            return ResponseInfo.fail();
        }

        return ResponseInfo.success(authUser);
    }

    @GetMapping("logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:login";
    }

}
