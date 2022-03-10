package com.qnnect.questions.dto;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.dto.CafeQuestionResponse;
import com.qnnect.cafe.dto.CafeUserResponse;
import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.dtos.CommentResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="질문리스트 댓글과 함께")
@Getter
public class QuestionDetailResponse {

    private QuestionMainResponse questionMainResponse;

    private List<CommentResponse> comments = new ArrayList<>();

//    public QuestionDetailResponse(Cafe entity, CafeUser currentCafeUser){
//        this.createdAt = entity.getCreatedAt().toLocalDate();
//        this.title = entity.getTitle();
//        this.code = entity.getCode();
//        this.diaryColor = entity.getDiaryColor();
//        this.currentUserResponse = CafeUserResponse.from(currentCafeUser);
//        this.cafeQuestionResponseList = CafeQuestionResponse.listFrom(entity.getCafeQuestions());
//        this.cafeUserResponseList = CafeUserResponse.listFrom(entity.getCafeUsers(), currentCafeUser);
//    }
}
