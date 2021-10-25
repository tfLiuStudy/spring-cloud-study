package cn.order.service;

import cn.common.domain.Order;
import com.alibaba.csp.sentinel.annotation.SentinelResource;

public interface OrderService {
    void save(Order order);

    void message();

    void createOrder(Order order);

    Order createOrder2(Integer pid);
}
