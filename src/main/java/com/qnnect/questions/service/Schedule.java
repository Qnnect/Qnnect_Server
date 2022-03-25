package com.qnnect.questions.service;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.repository.CafeRepository;
import com.qnnect.questions.repository.CafeQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class Schedule {

    private final CafeRepository cafeRepository;
    private final CafeQuestionRepository cafeQuestionRepository;
    private final CafeQuestionService cafeQuestionService;

//    @Scheduled(cron = "0 0 0 1/1 * * *")
//    public void sendQuestion() throws Exception {
//
//        LocalDateTime now = LocalDateTime.now();
//
//        List<Cafe> cafeList = cafeRepository.findAll();
//        List<Cafe> filteredCafe = cafeList.stream().filter(cafe -> cafe.getQuestionCycle().getValue()
//                == Duration.between(cafeQuestionRepository.findTop1ByCafe_IdOrderByIdAtDesc(cafe.getId())
//                .getCreatedAt(), now).toDays()).collect(Collectors.toList());
//
//        cafeQuestionService.sendCafeQuestions(filteredCafe);
//        log.info("질문 돌리기");
//    }

}
