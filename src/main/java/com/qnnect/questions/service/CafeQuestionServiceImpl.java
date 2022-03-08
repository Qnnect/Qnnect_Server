package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.repository.CafeRepository;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.Question;
import com.qnnect.questions.dto.QuestionCreateRequest;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.questions.repository.QuestionRepository;
import com.qnnect.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.TableGenerator;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CafeQuestionServiceImpl implements CafeQuestionService {

    private final CafeQuestionRepository cafeQuestionRepository;
    private final QuestionRepository questionRespository;
    private final CafeRepository cafeRepository;


    @Override
    public Question findQuestionToday(Cafe cafe) {
        System.out.println("service");
        CafeQuestion cafeQuestion = cafeQuestionRepository.findTop1ByCafe_IdOrderByCreatedAtDesc(cafe.getId());
        System.out.println(cafeQuestion);
        return cafeQuestion.getQuestions();
    }

    @Override
    @Transactional
    public void create(QuestionCreateRequest questionCreateRequest, User user) {
        Cafe cafe = cafeRepository.getById(questionCreateRequest.getCafeId());
        cafe.getGroupType().toString();
        Question question = questionRespository.save(Question.builder().content(questionCreateRequest.getContent())
                .questionType(cafe.getGroupType().toString()).user(user).build());
//        CafeQuestionRepository.save();// cafeQuestionWaitinglist에 추가
        // user point올려주기

    }
}
