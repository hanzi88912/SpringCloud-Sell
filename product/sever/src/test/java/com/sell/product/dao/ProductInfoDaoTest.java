package com.sell.product.dao;

import com.sell.product.enitity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
    @Autowired
    private ProductInfoDao dao;
    @Test
    public void findAllByCategoryType() {
        List<ProductInfo> allByCategoryType = dao.findByProductStatus(0);
        Assert.assertTrue(allByCategoryType.size() > 0);

    }
    @Test
    public void findByProductIdIn() {
        List<ProductInfo> products = dao.findByProductIdIn(Arrays.asList(new String[]{"157875196366160022", "157875227953464068"}));
        Assert.assertTrue(products.size() > 0);
    }
}