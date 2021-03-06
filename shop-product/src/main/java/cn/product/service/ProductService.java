package cn.product.service;

import cn.common.domain.Product;

public interface ProductService {

    Product findByPid(Integer pid);

    void reduceInventory(Integer pid, int num);
}
