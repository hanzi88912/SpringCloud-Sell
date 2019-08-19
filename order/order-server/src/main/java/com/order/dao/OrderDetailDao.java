package com.order.dao;

import com.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {
    public OrderDetail save(OrderDetail detail);
}
