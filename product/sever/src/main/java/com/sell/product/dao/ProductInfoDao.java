package com.sell.product.dao;

import com.sell.product.enitity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {
    public List<ProductInfo> findByProductStatus(Integer productStatus);
    public List<ProductInfo> findByProductIdIn(List<String> productIds);


}
