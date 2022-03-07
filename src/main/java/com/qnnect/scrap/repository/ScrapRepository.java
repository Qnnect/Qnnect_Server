package com.qnnect.scrap.repository;

import com.qnnect.scrap.domain.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    List<Scrap> findAllByUser_Id(@Param(value="userId") UUID userId);

    Scrap findByUser_IdAndQuestion_Id(@Param(value = "userId") UUID userId
            , @Param(value = "questionId") long questionId);

    Page<Scrap> findByUser_Id(@Param(value="userId")UUID userId, Pageable pageable);
}
