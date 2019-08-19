package com.order.dao;

import com.order.OrderApplicationTests;
import com.order.entity.OrderMaster;
import com.order.enums.OrderStatusEnum;
import com.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterDaoTest extends OrderApplicationTests {
    @Autowired OrderMasterDao dao;
    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456789");
        orderMaster.setBuyerName("pge");
        orderMaster.setBuyerPhone("13136581857");
        orderMaster.setBuyerAddress("yulongwan");
        orderMaster.setBuyerOpenid("wwwxxxwwww");
        orderMaster.setOrderAmount(new BigDecimal(100));
        orderMaster.setOrderStatus(OrderStatusEnum.CREATED.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster master = dao.save(orderMaster);
        Assert.assertTrue(master != null );
    }
}