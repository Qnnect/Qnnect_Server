package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.repository.CafeRepository;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.CafeQuestionWaitingList;
import com.qnnect.questions.domain.Question;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.questions.repository.CafeQuestionWaitingListRespository;
import com.qnnect.questions.repository.QuestionRepository;
import com.qnnect.user.domain.User;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CafeQuestionServiceImpl implements CafeQuestionService {

    private final CafeQuestionRepository cafeQuestionRepository;
    private final QuestionRepository questionRepository;
    private final CafeRepository cafeRepository;
    private final CafeQuestionWaitingListRespository cafeQuestionWaitingListRespository;
    private final UserRepository userRepository;

    @Override
    public Question findQuestionToday(Cafe cafe) {
        System.out.println("service");
        CafeQuestion cafeQuestion = cafeQuestionRepository.findTop1ByCafe_IdOrderByCreatedAtDesc(cafe.getId());
        System.out.println(cafeQuestion);
        return cafeQuestion.getQuestions();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(Long cafeId, String content, User user) {
        Cafe cafe = cafeRepository.getById(cafeId);
        cafe.getGroupType().toString();
        Question question = questionRepository.save(Question.builder().content(content)
                .questionType(cafe.getGroupType().toString()).user(user).build());

        cafeQuestionWaitingListRespository.save(CafeQuestionWaitingList.builder()
                .question(question).cafe(cafe).build());

        user.addPoint(10);
        userRepository.save(user);
        return question.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long questionId, String content) {
        Question question = questionRepository.getById(questionId);
        question.setContent(content);
        System.out.println(question.getContent());
        questionRepository.save(question);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
