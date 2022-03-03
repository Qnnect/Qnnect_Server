package com.qnnect.cafe.repository;

import com.qnnect.cafe.domain.CafeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeUserRepository extends JpaRepository <CafeUser, Long>{
    long countByCafe_Id(@Param(value = "cafeId") long cafeId);
}
