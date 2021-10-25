package cn.product.service.impl;

import cn.common.domain.Product;
import cn.product.dao.ProductDao;
import cn.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public void reduceInventory(Integer pid, int num) {
        Product product = productDao.findById(pid).get();
        if (product.getStock() < num) {
            throw new RuntimeException("库存不足");
        }
        int i = 1 / 0;
        product.setStock(product.getStock() - num);
        productDao.save(product);
    }
}
