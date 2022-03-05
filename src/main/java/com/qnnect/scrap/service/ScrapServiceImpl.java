package com.qnnect.scrap.service;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.scrap.domain.Scrap;
import com.qnnect.scrap.dtos.ScrapResponse;
import com.qnnect.scrap.repository.ScrapRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

//    public List<ScrapResponse> getAllScraps(User user){
//        List<Scrap> scrapList = scrapRepository.findAllByUser_Id(user.getId());
//        return new
//    }
}
