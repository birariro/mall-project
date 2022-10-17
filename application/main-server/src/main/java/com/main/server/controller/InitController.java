package com.main.server.controller;

import com.main.server.domain.Member;
import com.main.server.domain.Product;
import com.main.server.domain.repository.MemberRepository;
import com.main.server.domain.repository.ProductRepository;
import com.main.server.domain.value.Address;
import com.main.server.domain.vo.Email;
import com.main.server.domain.vo.LoginID;
import com.main.server.service.ProductService;
import com.main.server.service.member.LoginService;
import com.main.server.service.member.MemberService;
import com.main.server.service.order.OrderService;
import com.main.server.service.order.dto.OrderProducts;
import com.main.server.utils.ProductNameUtils;
import com.main.server.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/init")
public class InitController {

    private final LoginService loginService;
    private final OrderService orderService;
    private final ProductService productService;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @GetMapping("/member/{count}")
    public ResponseEntity initMembers(@PathVariable Long count){

        for(int i = 0 ; i <count ; i ++){
            String id = RandomUtils.getString(true, 5);
            String pwd = RandomUtils.getString(true, 10);
            String email = RandomUtils.getString(true, 5) + "@random.com";
            String city = RandomUtils.getString(false, 7);
            String street = RandomUtils.getString(false, 7);
            String zipcode = RandomUtils.getNumber(5);

            Address address = new Address(city,street,zipcode);
            loginService.join(new LoginID(id), pwd, new Email(email), address);
        }


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/product/{count}")
    public ResponseEntity initProduct(@PathVariable Long count){

        for(int i = 0 ; i <count ; i ++){

            String name = ProductNameUtils.getInstance().getRandomNickName();
            long productPrice = RandomUtils.getSingleNumber(100,90000);
            long productStockQuantity = RandomUtils.getSingleNumber(10,1000);
            productService.save(name, productPrice,productStockQuantity);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/order/{count}")
    public ResponseEntity initOrder(@PathVariable Long count){
        System.out.println("count = " + count);
        int memberSize = memberRepository.findAll().size();
        int productSize = productRepository.findAll().size();

        for(int i = 0 ; i <count ; i ++){

            Member member = memberRepository.findById((long) RandomUtils.getSingleNumber(1, memberSize - 1)).get();
            List<OrderProducts> products = new ArrayList<OrderProducts>();

            int orderLineSize = RandomUtils.getSingleNumber(1, 5);

            for(int j =0 ; j <orderLineSize; j++){
                Product product = productRepository.findById((long) RandomUtils.getSingleNumber(1, productSize - 1)).get();
                int productCount = RandomUtils.getSingleNumber(1, 10);
                products.add(new OrderProducts(product,(long) productCount));
            }

            orderService.post(member,products);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
