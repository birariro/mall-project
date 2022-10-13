package com.main.server.domain.repository;

import com.main.server.domain.Member;
import com.main.server.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndMember(Long id, Member member);
}
