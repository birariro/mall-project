package com.main.server.controller;

import com.main.server.common.response.ResponseService;
import com.main.server.common.response.result.CollectionResult;
import com.main.server.common.response.result.LinksResult;
import com.main.server.common.response.result.SingleResult;
import com.main.server.controller.dto.response.ProductDto;
import com.main.server.domain.Product;
import com.main.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ResponseService responseService;

    @GetMapping
    public ResponseEntity fetchProducts(Pageable page){

        Page<Product> products = productService.fetchPage(page);

        List<ProductDto> collect = products.stream().map(ProductDto::new).collect(Collectors.toList());

        List<LinksResult> links = new ArrayList<>();
        links.add(new LinksResult("detail","/product/{id}"));

        CollectionResult<ProductDto> body = responseService.getResult(collect,links);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity fetchProduct(@PathVariable Long id){

        Product products = productService.fetch(id);
        SingleResult<ProductDto> result = responseService.getResult(new ProductDto(products));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
