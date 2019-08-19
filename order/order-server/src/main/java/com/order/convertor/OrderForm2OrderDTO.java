package com.order.convertor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.order.dto.OrderDTO;
import com.order.entity.OrderDetail;
import com.order.enums.ResultEnum;
import com.order.exception.OrderException;
import com.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTO {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO  = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> detailList = new ArrayList<>();
        try {
            detailList =  gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());

        } catch (Exception e) {
            log.error("[json转换错误], string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setDetailList(detailList);
        return orderDTO;
    }
}
