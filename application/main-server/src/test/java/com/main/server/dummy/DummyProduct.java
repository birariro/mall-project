package com.main.server.dummy;


import com.main.server.domain.Product;
import com.main.server.domain.repository.ProductRepository;
import com.main.server.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class DummyProduct extends DummyMember{

    @Autowired
    private ProductService productService;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    EntityManager em;

    //@BeforeEach
    @Test
    public void createDummyArticle(){

        String productName = "testProductName";
        long productPrice = 13400;
        long productStockQuantity = 40;

        Product product = productService.save(productName, productPrice, productStockQuantity);
        Assertions.assertNotNull(product);


        Assertions.assertEquals(productName,product.getName());

    }
}
