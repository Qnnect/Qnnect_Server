package com.qnnect.scrap.repository;

import com.qnnect.scrap.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    public List<Scrap> findAllByUser_Id(@Param(value="cafeId") UUID cafeId);
}
