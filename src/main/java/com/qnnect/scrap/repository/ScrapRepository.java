package com.qnnect.scrap.repository;

import com.qnnect.scrap.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    public List<Scrap> findAllByUser_Id(@Param(value="userId") UUID userId);
    public Scrap findByUser_IdAndCafeQuestion_Id(@Param(value = "userId") UUID userId
            , @Param(value = "cafeQuestionId") long cafeQuestionId);
}
