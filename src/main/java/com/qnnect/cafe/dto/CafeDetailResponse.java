package com.qnnect.cafe.dto;

import com.qnnect.cafe.domain.Cafe;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.domain.EDiaryColor;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileResponse;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ApiModel(value = "카페")
public class CafeDetailResponse {

    private long cafeId;

    private long cafeUserId;

    private LocalDate createdAt;

    private String title;
    
    private String code;

    private EDiaryColor diaryColor;

    private CafeUserResponse currentUserResponse;

    private List<CafeUserResponse> cafeUserResponseList;

    private List<CafeQuestionResponse> cafeQuestionResponseList;

    public CafeDetailResponse(Cafe entity, CafeUser currentCafeUser, User user, List<Long> reportedId){
        this.createdAt = entity.getCreatedAt().toLocalDate();
        this.title = entity.getTitle();
        this.code = entity.getCode();
        this.cafeId = entity.getId();
        this.cafeUserId = currentCafeUser.getId();
        this.diaryColor = entity.getDiaryColor();
        this.currentUserResponse = CafeUserResponse.from(currentCafeUser, currentCafeUser.getUserDrinkSelected().get(currentCafeUser.getUserDrinkSelected().size()-1));
        this.cafeQuestionResponseList = CafeQuestionResponse.listFrom(entity.getCafeQuestions(), user, reportedId);
        this.cafeUserResponseList = CafeUserResponse.listFrom(entity.getCafeUsers(), currentCafeUser, reportedId);
    }
}
