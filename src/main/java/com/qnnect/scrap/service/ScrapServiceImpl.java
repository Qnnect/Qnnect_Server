package com.qnnect.scrap.service;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.scrap.domain.Scrap;
import com.qnnect.scrap.repository.ScrapRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService{

    private final ScrapRepository scrapRepository;
    private final CafeQuestionRepository cafeQuestionRepository;

    public void addScrap(User user, Long cafeQuestionId){
        CafeQuestion cafeQuestion = cafeQuestionRepository.getById(cafeQuestionId);
        scrapRepository.save(Scrap.builder()
                .user(user).cafeQuestion(cafeQuestion).build());
    }

//    public void getAllScrapQuestion(User user){
//        scrapRepository.findByUser_Id(user.getId());
//    }
}
