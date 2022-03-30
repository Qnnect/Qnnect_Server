package com.qnnect.questions.service;


import com.qnnect.questions.domain.Question;
import com.qnnect.questions.dto.WaitingListQuestionResponse;
import com.qnnect.questions.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    @Override
    public WaitingListQuestionResponse get(long questionId){
        Question question = questionRepository.getById(questionId);
        return WaitingListQuestionResponse.builder().questionId(question.getId())
                .createdAt(question.getCreatedAt().toLocalDate())
                .content(question.getContent()).build();
    }
}
