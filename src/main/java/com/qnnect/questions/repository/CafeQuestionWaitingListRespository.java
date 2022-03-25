package com.qnnect.questions.repository;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.CafeQuestionWaitingList;
import com.qnnect.questions.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeQuestionWaitingListRespository extends
        JpaRepository<CafeQuestionWaitingList, Long> {

    List<CafeQuestionWaitingList> findAllByCafe_Id(@Param(value = "cafeId") long cafeId);

    CafeQuestionWaitingList findByQuestion_Id(@Param(value = "questionId")long questionID);

    boolean existsByQuestion_Id(@Param(value = "questionId") long questionId);
}
