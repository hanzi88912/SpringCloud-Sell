package com.sell.product.service;

import com.sell.product.ProductApplicationTests;
import com.sell.product.common.DecreaseStockInput;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductInfoServiceTest extends ProductApplicationTests {
    @Autowired ProductInfoService productInfoService;
    @Test
    public void decreaseStock() {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875196366160022", 2);
        productInfoService.decreaseStock(Arrays.asList(decreaseStockInput));
    }
}