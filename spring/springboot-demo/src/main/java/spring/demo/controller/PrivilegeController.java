package spring.demo.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import spring.demo.annotation.PagerQueryParam;
import spring.demo.dto.PageQuery;
import spring.demo.dto.PrivilegeDto;
import spring.demo.dto.request.PrivilegeRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.service.IPrivilegeService;

/**
 * Created by facheng on 17-11-15.
 */

@RestController
@RequestMapping("/privileges")
public class PrivilegeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeController.class);

    @Resource
    private IPrivilegeService privilegeService;

    @PostMapping("/list")
    public ResponseInfo getList(@PagerQueryParam PageQuery pageQuery, @RequestBody PrivilegeRequest request) {
        return ResponseInfo.success(privilegeService.lists(pageQuery, PrivilegeDto.from(request)));
    }

    @PostMapping("")
    public ResponseInfo addPrivilege(@RequestBody PrivilegeRequest request) {

        try {
            privilegeService.addPrivilege(PrivilegeDto.from(request), getCurrentUserId());
            return ResponseInfo.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseInfo.fail(request);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseInfo delPrivilege(@PathVariable Long id) {

        try {
            privilegeService.deletePrivilege(id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseInfo.fail();
        }

        return ResponseInfo.success();
    }

    @PutMapping("/{id}")
    public ResponseInfo updatePrivilege(@PathVariable Long id, @RequestBody PrivilegeRequest request) {

        try {
            privilegeService.updatePrivilege(id, PrivilegeDto.from(request), getCurrentUserId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseInfo.fail(request);
        }
        return ResponseInfo.success();
    }

    @GetMapping("/{id}")
    public ResponseInfo getPrivilege(@PathVariable Long id) {
        return ResponseInfo.success(privilegeService.findPrivilegeDetail(id));
    }
}
