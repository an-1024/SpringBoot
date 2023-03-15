package com.anzhi.service.impl;

import com.anzhi.dao.OrderMapper;
import com.anzhi.entity.OrderEntity;
import com.anzhi.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Resource
    private OrderMapper orderMapper;
    
    @Override
    public List<OrderEntity> listByUserId(Long userId) {
        return orderMapper.getOrderInfoByUserId(userId);
    }
}
