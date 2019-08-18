package com.sell.product.enums;

import lombok.Getter;

@Getter
public enum ProductResultEnum {
    SUCCESS(0,"成功"),FAIL(1,"失敗")
    ,NOT_EXIST(2,"商品不存在"), ERROR_STOCK(3,"商品库存错误");
    private Integer code;
    private String msg;

    ProductResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
