package com.qnnect.scrap.dtos;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.dto.CafeMainResponse;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.dto.QuestionMainResponse;
import com.qnnect.scrap.domain.Scrap;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@ApiModel(value="전체 스크랩 리스트")
@Getter
@Builder
public class ScrapResponse {

//    private Long cafeQuestionId;

    private LocalDate createdAt;

    private String question;


    public static ScrapResponse from(Scrap scrap) {
        CafeQuestion cafeQuestion = scrap.getCafeQuestion();

        return ScrapResponse.builder()
                .createdAt(cafeQuestion.getCreatedAt().toLocalDate())
                .question(cafeQuestion.getQuestions().getContent())
                .build();
    }
}
