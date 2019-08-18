package com.sell.product.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductServer {
    @RequestMapping("/getProductMsg")
    public String getProductMsg(){
        return "msg from product2.";
    }
}
