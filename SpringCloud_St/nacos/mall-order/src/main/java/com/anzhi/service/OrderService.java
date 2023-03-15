package com.anzhi.service;

import com.anzhi.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> listByUserId(Long userId);
}
