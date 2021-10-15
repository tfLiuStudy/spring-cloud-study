package cn.order.service;

import cn.common.domain.Product;
import cn.order.service.sentinel.ProductServiceFallBack;
import cn.order.service.sentinel.ProductServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",
//        fallback = ProductServiceFallBack.class
        fallbackFactory = ProductServiceFallBackFactory.class // 获取具体的错误信息
    )
public interface ProductService {

    //指定调用提供者的哪个方法
    //@FeignClient+@GetMapping 就是一个完整的请求路径 http://serviceproduct/product/{pid}
    @GetMapping(value = "/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
