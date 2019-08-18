package com.sell.product.common;

import lombok.Data;

@Data
public class DecreaseStockInput {
    private String productId;
    private Integer productQuanity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuanity) {
        this.productId = productId;
        this.productQuanity = productQuanity;
    }
}
