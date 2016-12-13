package com.springboot.elasticsearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by feng on 16/12/14.
 */
@RestController
public class SearchController {


    @RequestMapping(value = "/init", method = PUT)
    public void initData() {

    }
}
