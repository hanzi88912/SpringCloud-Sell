package com.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    CREATED(0,"订单创建"),FINISH(1, "订单完成");
    private Integer code;
    private String msg;
    OrderStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
