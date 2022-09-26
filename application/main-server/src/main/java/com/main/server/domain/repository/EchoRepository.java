package com.main.server.domain.repository;

import com.main.server.domain.Echo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EchoRepository extends JpaRepository<Echo, Long> {
}
