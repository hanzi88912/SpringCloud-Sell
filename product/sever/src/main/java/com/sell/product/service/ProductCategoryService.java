package com.sell.product.service;

import com.sell.product.enitity.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    public List<ProductCategory> findProductCategoryListByTypes(List<Integer> categoryTypes );
}
