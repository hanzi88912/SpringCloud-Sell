package com.order.controller;

import com.order.config.GirlConifg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class GirlController {
    @Autowired
    private GirlConifg girlConifg;
    @GetMapping("/girlInfo")
    public String girlInfo() {
        return girlConifg.getName() + ":" + girlConifg.getAge();
    }
}
