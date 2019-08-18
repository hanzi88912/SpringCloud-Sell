package com.sell.product.exception;

import com.sell.product.enums.ProductResultEnum;

public class ProductException extends RuntimeException {
    private Integer code;
    private String message;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }
     public ProductException(ProductResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}
