package com.sell.product.service.impl;

import com.sell.product.common.DecreaseStockInput;
import com.sell.product.common.ProductInfoOutput;
import com.sell.product.dao.ProductInfoDao;
import com.sell.product.enitity.ProductInfo;
import com.sell.product.enums.ProductResultEnum;
import com.sell.product.exception.ProductException;
import com.sell.product.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productDao;
    @Override
    public List<ProductInfo> findProductInfoListByStatus(Integer productStatus) {
        return productDao.findByProductStatus(productStatus);
    }

    @Override
    public List<ProductInfoOutput> findByProductIdIn(List<String> productIds) {
        List<ProductInfoOutput> productInfoOutputList = productDao.findByProductIdIn(productIds).stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        return productInfoOutputList;
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputs) {
        for(DecreaseStockInput cartDTO : decreaseStockInputs) {
            Optional<ProductInfo> productInfoTmp = productDao.findById(cartDTO.getProductId());
            if (!productInfoTmp.isPresent()) {
                throw new ProductException(ProductResultEnum.NOT_EXIST);

            }
            ProductInfo productInfo = productInfoTmp.get();
            if (productInfo.getProductStock() - cartDTO.getProductQuanity() < 0) {
                throw new ProductException(ProductResultEnum.ERROR_STOCK);
            }
            productInfo.setProductStock(productInfo.getProductStock() - cartDTO.getProductQuanity());
            productDao.save(productInfo);


        }
    }


}
