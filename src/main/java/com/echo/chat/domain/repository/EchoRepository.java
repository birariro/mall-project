package com.echo.chat.domain.repository;

import com.echo.chat.domain.Echo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EchoRepository extends JpaRepository<Echo, Long> {
}
