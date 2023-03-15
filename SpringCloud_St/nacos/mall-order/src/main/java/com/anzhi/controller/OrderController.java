package com.anzhi.controller;

import com.anzhi.entity.OrderEntity;
import com.anzhi.service.OrderService;
import com.anzhi.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j@RequestMapping("/mallorder")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public R findOrderByUserId(@PathVariable("userId") Long userId) {

        //模拟异常
        if(userId == 5){
            throw new IllegalArgumentException("非法参数异常");
        }
        //模拟超时
        if(userId == 6){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("根据userId:"+userId+"查询订单信息");
        List<OrderEntity> orderEntities = orderService.listByUserId(userId);
        return R.ok().put("orders", orderEntities);
    }
}
