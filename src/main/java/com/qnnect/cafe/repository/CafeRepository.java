package com.qnnect.cafe.repository;


import com.qnnect.cafe.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
