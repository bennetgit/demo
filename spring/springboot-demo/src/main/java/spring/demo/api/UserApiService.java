package spring.demo.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.dto.UserDto;
import spring.demo.dto.response.ResponseInfo;


/**
 * Created by feng on 17/3/14.
 */
@RestController
@RequestMapping("/ws/users")
public class UserApiService {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    public ResponseInfo<UserDto> getUserInfo(@PathVariable("id") String id) {
        UserDto user = new UserDto();
        user.setId(12l);
        user.setCreatedOnStart(LocalDateTime.now());
        user.setUpdatedOn(LocalDateTime.now());
        user.setUsername("haha");
        return ResponseInfo.success(user);
    }
}
