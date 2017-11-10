package spring.demo.controller;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.demo.annotation.PagerQueryParam;
import spring.demo.dto.PageQuery;
import spring.demo.dto.UserDto;
import spring.demo.dto.request.UserRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.service.IUserService;
import spring.demo.util.PageResult;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @PostMapping(value = "/list")
    public ResponseInfo<PageResult<UserDto>> list(@PagerQueryParam PageQuery pageQuery,
            @RequestBody UserRequest userQuery) {
        return ResponseInfo.success(userService.getUserListByPage(pageQuery, UserDto.from(userQuery)));
    }

}
