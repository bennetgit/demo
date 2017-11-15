package spring.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.demo.dto.response.ResponseInfo;
import spring.demo.enums.ModuleType;

/**
 * Created by feng on 17/11/15.
 */

@RestController
@RequestMapping("/constant")
public class ConstantController {

    @GetMapping("/privilegeModule")
    public ResponseInfo getPrivilegeModule() {

        Map<String, Integer> resultMap = Stream.of(ModuleType.values())
                .collect(Collectors.toMap(ModuleType::getMessage, ModuleType::getConstant));

        return ResponseInfo.success(resultMap);
    }
}
