package com.order.controller;

import com.sell.product.client.ProductClient;
import com.sell.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class ClientController {
    @Autowired
    ProductClient productClient;
    @GetMapping("/getProductByIds")
    public String getProductByIds() {
        List<ProductInfoOutput> productByIds = productClient.listForOder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("response = {}", productByIds);
        return "ok";
    }
}
