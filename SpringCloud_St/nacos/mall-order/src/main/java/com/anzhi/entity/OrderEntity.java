package com.anzhi.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userId;
    /**
     * 商品编号
     */
    private String commodityCode;

    private Integer count;

    private Integer amount;
}
