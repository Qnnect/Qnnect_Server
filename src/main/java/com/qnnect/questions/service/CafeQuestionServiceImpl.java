package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.repository.CafeRepository;
import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.repository.CommentRepository;
import com.qnnect.likes.UserLikeQuestion;
import com.qnnect.likes.UserLikeQuestionRepository;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.CafeQuestionWaitingList;
import com.qnnect.questions.domain.Question;
import com.qnnect.questions.dto.CafeQuestionResponse;
import com.qnnect.questions.dto.QuestionDetailResponse;
import com.qnnect.questions.dto.QuestionResponse;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.questions.repository.CafeQuestionWaitingListRespository;
import com.qnnect.questions.repository.QuestionRepository;
import com.qnnect.scrap.repository.ScrapRepository;
import com.qnnect.user.domain.Report;
import com.qnnect.user.domain.User;
import com.qnnect.user.repositories.ReportRepository;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CafeQuestionServiceImpl implements CafeQuestionService {

    private final CafeQuestionRepository cafeQuestionRepository;
    private final QuestionRepository questionRepository;
    private final CafeRepository cafeRepository;
    private final CafeQuestionWaitingListRespository cafeQuestionWaitingListRespository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ScrapRepository scrapRepository;
    private final UserLikeQuestionRepository likeRepository;
    private final ReportRepository reportRepository;

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
    public void update(Long cafeQuestionId, String content) {
        CafeQuestion cafeQuestion = cafeQuestionRepository.getById(cafeQuestionId);
        Question question = cafeQuestion.getQuestions();
        question.setContent(content);
        questionRepository.save(question);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long cafeQuestionId) {
        CafeQuestion cafeQuestion = cafeQuestionRepository.getById(cafeQuestionId);
        Question question = cafeQuestion.getQuestions();
        questionRepository.deleteById(question.getId());
    }

    @Transactional
    public QuestionDetailResponse getQuestion(Long cafeQuestionId, User user) {
        CafeQuestion cafeQuestion = cafeQuestionRepository.getById(cafeQuestionId);
        log.info("getting cafeQuestion");
        List<Comment> comments = commentRepository.findAllByCafeQuestion_Id(cafeQuestionId);
        boolean isScraped = scrapRepository.existsByUser_IdAndCafeQuestion_Id(user.getId()
                ,cafeQuestion.getId());
        System.out.println(user.getNickName());
        System.out.println("isScraped" + isScraped);
        boolean isLiked = likeRepository.existsByUser_IdAndQuestion_Id(user.getId(),
                cafeQuestion.getQuestions().getId());
        Comment currentUserComment = commentRepository.findByUser_IdAndCafeQuestion_Id( user.getId(), cafeQuestionId);
        List<Report> report = reportRepository.findAllByUserId(user.getId());
        List<Long> reportedUser = report.stream().map(Report::getReportedId).collect(Collectors.toList());
        return new QuestionDetailResponse(cafeQuestion, comments, user, isScraped,isLiked, currentUserComment, reportedUser);
    }

    @Override
    @Transactional
    public CafeQuestionResponse getCafeQuestions(Long cafeId, Pageable pageable) {
        List<CafeQuestion> cafeQuestion = cafeQuestionRepository.findAllByCafe_Id(cafeId, pageable);
        return CafeQuestionResponse.from(cafeQuestion);
    }

    @Override
    @Transactional
    public CafeQuestionResponse searchCafeQuestions(Long cafeId, String word,Pageable pageable) {
        List<CafeQuestion> cafeQuestion = cafeQuestionRepository.findByCafe_IdAndWord(cafeId, word,pageable);

        return CafeQuestionResponse.from(cafeQuestion);
    }
}
