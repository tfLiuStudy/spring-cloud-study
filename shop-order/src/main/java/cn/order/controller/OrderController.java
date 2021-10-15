package cn.order.controller;

import cn.common.domain.Order;
import cn.common.domain.Product;
import cn.order.service.OrderService;
import cn.order.service.ProductService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@Api(value = "/order", tags = "订单服务")
@RequestMapping("/order")
public class OrderController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient discoveryClient;

    //准备买1件商品
    @GetMapping("/prod/{pid}")
    @ApiOperation(value = "订单查询", notes = "订单查询")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        //调用商品微服务,查询商品信息
        Product product = productService.findByPid(pid);
        if (product.getPid() == -1) {
            Order order = new Order();
            order.setPname("下单失败");
            return order;
        }
        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));
        //下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderService.createOrder(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order;
    }
}
