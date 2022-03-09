package com.qnnect.questions.repository;

import com.qnnect.questions.domain.CafeQuestionWaitingList;
import com.qnnect.questions.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeQuestionWaitingListRespository extends
        JpaRepository<CafeQuestionWaitingList, Long> {
}
