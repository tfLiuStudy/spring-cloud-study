package cn.order.controller;

import cn.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController3 {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/message1")
    public String message1() {
        this.orderService.message();
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2() {
        this.orderService.message();
        return "message2";
    }
}
