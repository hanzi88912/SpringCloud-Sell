package com.order.dao;

import com.order.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
    public OrderMaster save(OrderMaster master);
}
