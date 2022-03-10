package com.qnnect.questions.dto;

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
}
