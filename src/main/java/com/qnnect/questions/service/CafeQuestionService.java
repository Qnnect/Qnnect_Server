package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.questions.domain.Question;


public interface CafeQuestionService {
    Question findQuestionToday(Cafe cafe);
}
