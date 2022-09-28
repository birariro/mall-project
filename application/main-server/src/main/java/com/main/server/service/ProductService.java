package com.main.server.service;

import com.main.server.domain.Product;
import com.main.server.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(String productName, long productPrice, long productStockQuantity) {

        Product product = new Product(productName, productPrice, productStockQuantity);
        return productRepository.save(product);
    }

    public Product fetch(Long productId){

        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("not exist product"));
    }

}
