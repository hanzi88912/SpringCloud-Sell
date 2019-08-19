package com.order.controller;

import com.order.convertor.OrderForm2OrderDTO;
import com.order.dto.OrderDTO;
import com.order.enums.ResultEnum;
import com.order.exception.OrderException;
import com.order.form.OrderForm;
import com.order.service.OrderService;
import com.order.util.ResultVOUtil;
import com.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.校验参数
 * 2.查询商品信息（调用productService）
 * 3.计算总价
 * 4.扣库存
 * 5.订单入库
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult result){
        if (result.hasErrors()) {
            log.error("创建订单错误，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), result.getFieldError().getDefaultMessage());

        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getDetailList())) {
            log.error("购物车为空，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO dto = orderService.save(orderDTO);
        Map<String, String> objMap = new HashMap<>();
        objMap.put("orderId", dto.getOrderId());

        return ResultVOUtil.success(objMap);

    }
}
