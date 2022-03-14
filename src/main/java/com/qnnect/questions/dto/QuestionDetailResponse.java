package com.qnnect.questions.dto;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.dto.CafeQuestionResponse;
import com.qnnect.cafe.dto.CafeUserResponse;
import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.dtos.CommentResponse;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.user.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="질문리스트 댓글과 함께")
@Getter
public class QuestionDetailResponse {

    private CafeQuestionResponse questionMainResponse;

    private boolean isLiked;

    private boolean isScraped;

    private List<CommentResponse> comments = new ArrayList<>();

    public QuestionDetailResponse(CafeQuestion cafeQuestion, List<Comment> comments, User user, boolean isScraped, boolean isLiked){
        this.questionMainResponse = CafeQuestionResponse.from(cafeQuestion, user);
        this.isLiked = isLiked;
        this.isScraped = isScraped;
        this.comments = CommentResponse.listFrom(comments);
    }
}
