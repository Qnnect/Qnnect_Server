package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.Question;
import com.qnnect.questions.dto.QuestionCreateRequest;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeQuestionServiceImpl implements CafeQuestionService {

    private final CafeQuestionRepository cafeQuestionRepository;


//    @Override
//    Question findQuestionToday(Cafe cafe) {
//        System.out.println("service");
//        CafeQuestion cafeQuestion = cafeQuestionRepository.findTop1ByCafe_IdOrderByCreatedAtDesc(cafe.getId());
//        System.out.println(cafeQuestion);
//        return cafeQuestion.getQuestions();
//    }

    @Override
    public void create(QuestionCreateRequest questionCreateRequest, User user) {

    }
}
