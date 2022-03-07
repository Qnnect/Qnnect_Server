package com.qnnect.cafe.dto;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.EQuestionerType;
import com.qnnect.user.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ApiModel(value = "카페 질문")
public class CafeQuestionResponse {

    private LocalDate createdAt;
    private long daysLeft;
    private String questioner;
    private String question;

    public static CafeQuestionResponse from(CafeQuestion cafeQuestion) {

        return CafeQuestionResponse.builder()
                .createdAt(cafeQuestion.getCreatedAt().toLocalDate())
                .daysLeft(calculateDaysLeft(cafeQuestion))
                .question(cafeQuestion.getQuestions().getContent())
                .questioner(findQuestioner(cafeQuestion))
                .build();
    }

    public static String findQuestioner(CafeQuestion cafeQuestion){
        if(cafeQuestion.getQuestions().getQuestionerType() == EQuestionerType.admin){
            return "넥트";
        }else{
            User user = cafeQuestion.getQuestions().getQuestionUserMade().getUser();
            return user.getNickName();
        }
    }

    public static long calculateDaysLeft(CafeQuestion cafeQuestion){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, cafeQuestion.getCreatedAt());
        Long daysLeft = 7 - duration.toDays();
        return daysLeft;
    }

    public static List<CafeQuestionResponse> listFrom(List<CafeQuestion> cafeQuestionList) {
        if(cafeQuestionList == null){
            return null;
        }
        return cafeQuestionList.stream()
                .map(CafeQuestionResponse::from)
                .collect(Collectors.toList());
    }
}
