package com.qnnect.questions.dto;

import com.qnnect.comments.domain.Comments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value="질문리스트 댓글과 함께")
@Getter
public class QuestionDetailResponse {
    @ApiModelProperty(value = "다이어리 질문 리스트 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "내용", example = "친구와 함께 가장 가고 싶은 외국 여행지는 어딘가요?")
    private String content;

    @ApiModelProperty(value = "댓글", example = "친구와 함께 가장 가고 싶은 외국 여행지는 어딘가요?")
    private List<Comments> comments = new ArrayList<>();
}
