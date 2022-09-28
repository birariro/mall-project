package com.main.server.service;

import com.main.server.domain.Member;
import com.main.server.domain.Product;
import com.main.server.domain.repository.MemberRepository;
import com.main.server.domain.repository.ProductRepository;
import com.main.server.dummy.DummyProduct;
import com.main.server.service.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderTest extends DummyProduct {

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void createOrder(){

        List<Product> productAll = productRepository.findAll();
        Assertions.assertTrue(productAll.size() > 0);

        List<Member> memberAll = memberRepository.findAll();
        Assertions.assertTrue(memberAll.size() > 0);
    }
}
