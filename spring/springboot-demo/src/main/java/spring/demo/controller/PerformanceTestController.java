package spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.demo.annotation.WebLog;
import spring.demo.dto.PerformanceTestDto;
import spring.demo.dto.request.PerformanceTestRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.service.IPerformanceTestService;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by facheng on 15.03.17.
 */
@Controller
@RequestMapping("/test")
@ApiIgnore
@WebLog
public class PerformanceTestController {

    @Resource
    private IPerformanceTestService performanceTestService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "performance";
    }

    @ResponseBody
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseInfo<PerformanceTestDto> startTest(@RequestBody PerformanceTestRequest request) {
        try {
            performanceTestService.startTest(request);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseInfo.fail();
        }

        return ResponseInfo.success(null);
    }
}
