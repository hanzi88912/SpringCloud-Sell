package com.sell.product.controller;

import com.sell.product.common.DecreaseStockInput;
import com.sell.product.common.ProductInfoOutput;
import com.sell.product.enitity.ProductCategory;
import com.sell.product.enitity.ProductInfo;
import com.sell.product.enums.ProductResultEnum;
import com.sell.product.enums.ProductStatusEnum;
import com.sell.product.service.ProductCategoryService;
import com.sell.product.service.ProductInfoService;
import com.sell.product.vo.ProductCategoryVO;
import com.sell.product.vo.ProductInfoVO;
import com.sell.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO<ProductCategoryVO> list() {
        ResultVO<ProductCategoryVO> result  = new ResultVO<ProductCategoryVO>();
        List<ProductInfo> productInfos = productInfoService.findProductInfoListByStatus(ProductStatusEnum.UP.getCode());
        //Lamada
        List<Integer> categoryList = productInfos.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = productCategoryService.findProductCategoryListByTypes(categoryList);
        List<ProductCategoryVO> data = new ArrayList<ProductCategoryVO>();

        for (ProductCategory category : productCategoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCategoryName(category.getCategoryName());
            productCategoryVO.setCategoryType(category.getCategoryType());
            List<ProductInfoVO> foods = new ArrayList<ProductInfoVO>();
            for (ProductInfo product : productInfos){
                if (product.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(product, productInfoVO);
                    foods.add(productInfoVO);
                }
            }
            productCategoryVO.setFoods(foods);
            data.add(productCategoryVO);

        }
        result.setCode(ProductResultEnum.SUCCESS.getCode());
        result.setMsg(ProductResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;

    }

    /**
     * 给订单服务调用
     * @param productIds
     * @return
     */
    @PostMapping("/listForOrder")
    List<ProductInfoOutput> listForOrder (@RequestBody List<String> productIds) {
       return productInfoService.findByProductIdIn(productIds);
    }

    @PostMapping("/decreaseStock")
    void decreaseStock (@RequestBody List<DecreaseStockInput> decreaseStockInputs) {
        productInfoService.decreaseStock(decreaseStockInputs);
    }

}
