package com.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    private String name;
    @NotEmpty(message = "地址必填")
    private String address;
    @NotEmpty(message = "手机号必填")
    private String phone;
    @NotEmpty(message = "openId必填")
    private String openid;
    private String items;
}
