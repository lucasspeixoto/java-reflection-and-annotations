package com.basicsstrong.spring;

import java.util.List;

import com.basicsstrong.annotation.Autowired;
import com.basicsstrong.annotation.Component;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getFinalPrice(List<Product> items) {
        List<Product> list = repo.getPrice(items);

        for (Product product : list) {
            product.setPrice(product.getPrice() * (100 - product.getDiscount()) / 100);
            System.out.println("Price of " + product.getName() + " after " + product.getDiscount().toString() + "% discount is " + product.getPrice().toString() + "$.");
        }

        return list;

    }
}
