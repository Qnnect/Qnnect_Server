package com.qnnect.cafe.dto;

import com.qnnect.cafe.domain.Cafe;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.user.dtos.ProfileResponse;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ApiModel(value = "카페")
public class CafeDetailResponse {

    private LocalDateTime createdAt;

    private String title;

    private ProfileResponse organizer;
    
    private String code;

    private List<CafeUserResponse> cafeUserResponseList;

    private List<CafeQuestionResponse> cafeQuestionResponseList;

    public CafeDetailResponse(Cafe entity){
        this.createdAt = entity.getCreatedAt();
        this.title = entity.getTitle();
        this.organizer = ProfileResponse.from(entity.getOrganizer());
        this.code = entity.getCode();
        List<CafeQuestion> cafeQuestions = entity.getCafeQuestions();
        this.cafeQuestionResponseList = CafeQuestionResponse.listFrom(cafeQuestions);
        this.cafeUserResponseList = CafeUserResponse.listFrom(entity.getCafeUsers());
    }
}
