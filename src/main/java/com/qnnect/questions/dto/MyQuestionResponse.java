package com.qnnect.questions.dto;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.CafeQuestionWaitingList;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder
public class MyQuestionResponse {

    private Long questionId;

    private LocalDate createdAt;

    private String question;

    private boolean isQuestionWaiting;

    private String cafeTitle;


    public static MyQuestionResponse fromCafeQuestion(CafeQuestion cafeQuestion){
        if(cafeQuestion == null){
            return null;
        }
        return MyQuestionResponse.builder()
                .isQuestionWaiting(false)
                .questionId(cafeQuestion.getId())
                .createdAt(cafeQuestion.getQuestions().getCreatedAt().toLocalDate())
                .question(cafeQuestion.getQuestions().getContent())
                .cafeTitle(cafeQuestion.getCafe().getTitle())
                .build();
    }

    public static MyQuestionResponse fromWaitingList(CafeQuestionWaitingList cafeQuestionListWaitingList){
        return MyQuestionResponse.builder()
                .isQuestionWaiting(true)
                .questionId(cafeQuestionListWaitingList.getQuestion().getId())
                .createdAt(cafeQuestionListWaitingList.getCreatedAt().toLocalDate())
                .question(cafeQuestionListWaitingList.getQuestion().getContent())
                .cafeTitle(cafeQuestionListWaitingList.getCafe().getTitle())
                .build();
    }

    public static List<MyQuestionResponse> listFromAllQuestions(List<CafeQuestion> cafeQuestionList,
                                                              List<CafeQuestionWaitingList> cafeQuestionWaitingList) {
        List<MyQuestionResponse> cafeQuestionResponse = new ArrayList<>();
        List<MyQuestionResponse> cafeQuestionWaitingResponse = new ArrayList<>();

        if(cafeQuestionWaitingList.size() != 0){
            cafeQuestionWaitingResponse = cafeQuestionWaitingList.stream()
                    .map(MyQuestionResponse::fromWaitingList)
                    .collect(Collectors.toList());
        }
        if(cafeQuestionList.size() != 0){
            cafeQuestionResponse = cafeQuestionList.stream()
                    .map(MyQuestionResponse::fromCafeQuestion)
                    .collect(Collectors.toList());
        }

        List<MyQuestionResponse> totalQuestionResponse = Stream.of(cafeQuestionWaitingResponse, cafeQuestionResponse)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());

        return totalQuestionResponse;
    }
}
