package com.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderDetailForm {
    @NotEmpty(message = "商品ID不能为空")
    private String productId;
    @NotEmpty(message = "商品数量不能为空")
    private Integer productQuantity;
}
