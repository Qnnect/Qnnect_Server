package com.qnnect.questions.repository;

import com.qnnect.questions.domain.Question;
import com.qnnect.scrap.domain.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUser_Id(@Param(value="userId") UUID userId, Pageable pageable);

}
