package com.anzhi.dao;

import com.anzhi.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderMapper {
    
    default List<OrderEntity> getOrderInfoByUserId(Long userId){
        OrderEntity orderEntity = OrderEntity.builder().amount(10).id(1234L).count(9)
                .userId("1234567").commodityCode("315315")
                .build();

        List<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityList.add(orderEntity);

        return orderEntityList;
    }
}
