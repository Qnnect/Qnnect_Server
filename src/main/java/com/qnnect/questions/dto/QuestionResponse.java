package com.qnnect.questions.dto;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.CafeQuestionWaitingList;
import com.qnnect.questions.domain.Question;
import com.qnnect.scrap.domain.Scrap;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@ApiModel(value="전체 스크랩 리스트")
@Getter
@Builder
public class QuestionResponse {

    private Long cafeQuestionId;

    private LocalDate createdAt;

    private String question;

    private String cafeTitle;


    public static QuestionResponse from(Scrap scrap) {
        Question question = scrap.getCafeQuestion().getQuestions();

        return QuestionResponse.builder()
                .cafeQuestionId(scrap.getCafeQuestion().getId())
                .createdAt(scrap.getCreatedAt().toLocalDate())
                .question(question.getContent())
                .cafeTitle(scrap.getCafeQuestion().getCafe().getTitle())
                .build();
    }

    public static QuestionResponse from(CafeQuestion cafeQuestion) {

        return QuestionResponse.builder()
                .cafeQuestionId(cafeQuestion.getId())
                .createdAt(cafeQuestion.getCreatedAt().toLocalDate())
                .question(cafeQuestion.getQuestions().getContent())
                .cafeTitle(cafeQuestion.getCafe().getTitle())
                .build();
    }

    public static QuestionResponse from(CafeQuestionWaitingList cafeQuestion) {

        return QuestionResponse.builder()
                .cafeQuestionId(cafeQuestion.getId())
                .createdAt(cafeQuestion.getCreatedAt().toLocalDate())
                .question(cafeQuestion.getQuestion().getContent())
                .cafeTitle(cafeQuestion.getCafe().getTitle())
                .build();
    }

    public static List<QuestionResponse> listFromCafeQuestion(List<CafeQuestion> cafeQuestionList) {
        if(cafeQuestionList == null){
            return null;
        }

        return cafeQuestionList.stream()
                .map(QuestionResponse::from)
                .collect(Collectors.toList());
    }

    public static List<QuestionResponse> listFromCafeQuestionWaitingList(List<CafeQuestionWaitingList> cafeQuestionWaitingList) {
        if(cafeQuestionWaitingList == null){
            return null;
        }

        return cafeQuestionWaitingList.stream()
                .map(QuestionResponse::from)
                .collect(Collectors.toList());
    }
}
