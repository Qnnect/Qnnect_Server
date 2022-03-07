package com.qnnect.scrap.service;

import com.qnnect.common.exception.cafe.QuestionNotScrappedException;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.scrap.domain.Scrap;
import com.qnnect.scrap.dtos.ScrapResponse;
import com.qnnect.scrap.repository.ScrapRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService {

    private final ScrapRepository scrapRepository;
    private final CafeQuestionRepository cafeQuestionRepository;

    @Override
    public void addScrap(User user, Long cafeQuestionId) {
        CafeQuestion cafeQuestion = cafeQuestionRepository.getById(cafeQuestionId);
        scrapRepository.save(Scrap.builder()
                .user(user).cafeQuestion(cafeQuestion).build());
    }

    @Override
    public void deleteScrap(User user, Long cafeQuestionId) {
        try {
            log.info("getting scrap");
            Scrap scrap = scrapRepository.findByUser_IdAndCafeQuestion_Id(user.getId(), cafeQuestionId);
            log.info("deleting scrap");
            scrapRepository.delete(scrap);
            log.info("deleted scrap");
        } catch (IllegalArgumentException e) {
            throw new QuestionNotScrappedException();
        }
    }

//    public List<ScrapResponse> getAllScraps(User user){
//        List<Scrap> scrapList = scrapRepository.findAllByUser_Id(user.getId());
//        return new
//    }
}
