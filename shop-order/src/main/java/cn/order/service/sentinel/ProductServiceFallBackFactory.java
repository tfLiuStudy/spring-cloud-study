//package cn.order.service.sentinel;
//
//import cn.common.domain.Product;
//import cn.order.service.ProductService;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 拿到具体的错误信息
// */
//@Component
//public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
//    @Override
//    public ProductService create(Throwable throwable) {
//        return pid -> {
//            throwable.printStackTrace();
//            Product product = new Product();
//            product.setPid(-1);
//            return product;
//        };
//    }
//}
