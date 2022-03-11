package com.qnnect.questions.repository;

import com.qnnect.questions.domain.CafeQuestionWaitingList;
import com.qnnect.questions.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CafeQuestionWaitingListRespository extends
        JpaRepository<CafeQuestionWaitingList, Long> {
    List<CafeQuestionWaitingList> findAllByCafe_Id(@Param(value = "cafeId") long cafeId);
}
