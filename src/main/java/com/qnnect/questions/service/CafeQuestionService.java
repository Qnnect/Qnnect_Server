package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.questions.domain.Question;
import com.qnnect.user.domain.User;


public interface CafeQuestionService {
    public Question findQuestionToday(Cafe cafe);

    Long create(Long cafeId, String content, User user);
    void update(Long questionId, String content);
    void delete(Long questionId);
}
