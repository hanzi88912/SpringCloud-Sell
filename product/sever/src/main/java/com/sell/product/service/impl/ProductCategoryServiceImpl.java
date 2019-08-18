package com.sell.product.service.impl;

import com.sell.product.dao.ProductCategoryDao;
import com.sell.product.enitity.ProductCategory;
import com.sell.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao categoryDao;

    @Override
    public List<ProductCategory> findProductCategoryListByTypes(List<Integer> categoryTypes) {
        return categoryDao.findByCategoryTypeIn(categoryTypes);
    }
}
