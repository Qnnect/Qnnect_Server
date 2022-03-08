package com.qnnect.scrap.service;

import com.qnnect.common.CurrentUser;
import com.qnnect.common.exception.cafe.QuestionNotScrappedException;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.scrap.domain.Scrap;
import com.qnnect.scrap.dtos.ScrapResponse;
import com.qnnect.scrap.repository.ScrapRepository;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.MainResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

            Scrap scrap = scrapRepository.findByUser_IdAndCafeQuestion_Id(user.getId(), cafeQuestionId);
            log.info("deleting scrap");
            scrapRepository.delete(scrap);
            log.info("deleted scrap");
        } catch (IllegalArgumentException e) {
            throw new QuestionNotScrappedException();
        }
    }

//    @Override
//    public List<ScrapResponse> getAllScraps(Pageable pageable, User user){
//
//        List<ScrapResponse> scrapList = scrapRepository.findByUser_Id(user.getId(),pageable)
//                .stream()
//                .map(ScrapResponse::from)
//                .collect(Collectors.toList());
//        return scrapList;
//    }

    @Override
    public List<ScrapResponse> getCafeScraps(Pageable pageable, User user, Long cafeId ){

        List<ScrapResponse> scrapList = scrapRepository.findByUser_IdAndCafe_Id(user.getId(), cafeId ,pageable)
                .stream()
                .map(ScrapResponse::from)
                .collect(Collectors.toList());
        return scrapList;
    }


}
