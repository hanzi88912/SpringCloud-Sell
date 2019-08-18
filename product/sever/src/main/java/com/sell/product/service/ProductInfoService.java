package com.sell.product.service;

import com.sell.product.common.DecreaseStockInput;
import com.sell.product.common.ProductInfoOutput;
import com.sell.product.enitity.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    public List<ProductInfo> findProductInfoListByStatus(Integer productStatus);
    public List<ProductInfoOutput> findByProductIdIn(List<String> productIds);

    void decreaseStock(List<DecreaseStockInput> decreaseStockInputs);

}
