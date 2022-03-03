package com.qnnect.cafe.repository;

import com.qnnect.cafe.domain.CafeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeUserRepository extends JpaRepository<CafeUser, Long> {
    long countByCafe(Long cafeId);
}
