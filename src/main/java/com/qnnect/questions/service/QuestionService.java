package com.qnnect.questions.service;

import com.qnnect.questions.dto.WaitingListQuestionResponse;

public interface QuestionService {
    public WaitingListQuestionResponse get(long questionId);
}
