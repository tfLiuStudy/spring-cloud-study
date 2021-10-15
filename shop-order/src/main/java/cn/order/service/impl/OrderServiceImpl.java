package cn.order.service.impl;

import cn.common.domain.Order;
import cn.order.dao.OrderDao;
import cn.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    @SentinelResource("message")
    public void message() {
        System.out.println("message");
    }

    @Override
    public void createOrder(Order order) {
        System.out.println("createOrder");
    }
}
