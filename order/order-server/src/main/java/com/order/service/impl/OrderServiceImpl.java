package com.order.service.impl;

import com.order.dao.OrderDetailDao;
import com.order.dao.OrderMasterDao;
import com.order.dto.OrderDTO;
import com.order.entity.OrderDetail;
import com.order.entity.OrderMaster;
import com.order.enums.OrderStatusEnum;
import com.order.enums.PayStatusEnum;
import com.order.service.OrderService;
import com.order.util.KeyUtil;
import com.sell.product.client.ProductClient;
import com.sell.product.common.DecreaseStockInput;
import com.sell.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMasterDao masterDao;
    @Autowired
    OrderDetailDao detailDao;
    @Autowired
    ProductClient productClient;

    /**
     * * 1.校验参数
     *  * 2.查询商品信息（调用productService）
     *  * 3.计算总价
     *  * 4.扣库存
     *  * 5.订单入库
     * @param oderDTO
     * @return
     */
    @Override
    public OrderDTO save(OrderDTO oderDTO) {
        //查询商品信息
        List<String> productIds = oderDTO.getDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfos = productClient.listForOder(productIds);

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(oderDTO, orderMaster);
        String orderId  = KeyUtil.generateKey();
        orderMaster.setOrderId(orderId);
        orderMaster.setPayStatus(PayStatusEnum.FINISH.getCode());
//        orderMaster.setOrderAmount(new BigDecimal(99));
        BigDecimal orderAmount = BigDecimal.ZERO;
        orderMaster.setOrderStatus(OrderStatusEnum.CREATED.getCode());
        for(OrderDetail detail : oderDTO.getDetailList()) {
            for(ProductInfoOutput productInfo : productInfos) {
                if (productInfo.getProductId().equals(detail.getProductId())) {
                    BeanUtils.copyProperties(productInfo, detail);
                    detail.setDetailId(KeyUtil.generateKey());
                    detail.setOrderId(orderId);

                    orderAmount = orderAmount.add(detail.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity())));

                    detailDao.save(detail);

                }
            }
        }
        List<DecreaseStockInput> cartDTOS = oderDTO.getDetailList().stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        //扣库存
        productClient.decreaseStock(cartDTOS);
        orderMaster.setOrderAmount(orderAmount);
        masterDao.save(orderMaster);


        oderDTO.setOrderId(orderMaster.getOrderId());
        return oderDTO;
    }
}
