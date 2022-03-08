package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.questions.domain.Question;
import com.qnnect.questions.dto.QuestionCreateRequest;
import com.qnnect.user.domain.User;


public interface CafeQuestionService {
    public Question findQuestionToday(Cafe cafe);
    void create(QuestionCreateRequest questionCreateRequest, User user);
}
