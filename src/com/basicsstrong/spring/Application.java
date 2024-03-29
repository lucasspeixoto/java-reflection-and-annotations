package com.basicsstrong.spring;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        ProductService productService = context.getBean(ProductService.class);

        List<Product> items = new ArrayList<>();

        items.add(new Product("Yoga Mat", 40));
        items.add(new Product("Coffee", 10));
        items.add(new Product("Tea", 20));

        List<Product> finalPrice = productService.getFinalPrice(items);

        for (Product product : finalPrice) {
            System.out.println(product.getName() + " @"+ product.getDiscount().toString() + " % discount :"+ product.getPrice().toString() +"$." );
        }
    }
}
