package com.sell.product.dao;

import com.sell.product.ProductApplicationTests;
import com.sell.product.enitity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductCategoryDaoTest extends ProductApplicationTests {
    @Autowired
    private ProductCategoryDao dao;
    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categoryes = dao.findByCategoryTypeIn(Arrays.asList(new Integer[]{11,22}));
        Assert.assertTrue(categoryes.size() > 0);
    }
}