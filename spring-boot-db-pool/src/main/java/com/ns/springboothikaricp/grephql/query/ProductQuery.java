package com.ns.springboothikaricp.grephql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.ns.springboothikaricp.bean.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductQuery implements GraphQLQueryResolver {

    public List<Product> getProducts(Long userId){
        List<Product>  products=Lists.newArrayList();
        Product product = new Product();
        product.setUserId(userId);
        if(userId==1L){
            product.setCode("001");
            product.setName("p001");
        }
        if(userId==2L){
            product.setCode("002");
            product.setName("p002");
        }
        products.add(product);

        return products;
    }


}
