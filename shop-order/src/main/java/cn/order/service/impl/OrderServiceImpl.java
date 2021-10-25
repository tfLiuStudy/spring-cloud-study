package cn.order.service.impl;

import cn.common.domain.Order;
import cn.common.domain.Product;
import cn.order.dao.OrderDao;
import cn.order.service.OrderService;
import cn.order.service.ProductService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

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

    @Override
    @GlobalTransactional
    public Order createOrder2(Integer pid) {
        //1 调用商品微服务,查询商品信息
        Product product = productService.findByPid(pid);
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));
        //2 下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        //3 扣库存
        productService.reduceInventory(pid, order.getNumber());
        //4 向mq中投递一个下单成功的消息
        rocketMQTemplate.convertAndSend("order-topic", order);
        return order;
    }
}
