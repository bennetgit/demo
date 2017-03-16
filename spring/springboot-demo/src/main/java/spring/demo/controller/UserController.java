package spring.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.demo.dto.request.UserRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.dto.UserDto;
import spring.demo.util.PageResult;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/")
@ApiIgnore
public class UserController {

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String userIndex(ModelAndView view) {
        return "user";
    }

    @RequestMapping(value = "users/list", method = RequestMethod.POST)
    @ResponseBody
    public PageResult<UserDto> userList(@RequestBody UserRequest request) {

        List<UserDto> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new UserDto(Long.valueOf(i), "test" + i, LocalDateTime.now(), LocalDateTime.now()));
        }

        return new PageResult<>(100l, users);

    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfo<UserDto> createUser(@RequestBody UserRequest request) {
        return ResponseInfo.success(null);
    }

}