package com.qnnect.questions.dto;

import com.qnnect.comments.domain.Comment;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class QuestionCreateRequest {
    @ApiModelProperty(value = "카페 id", example = "1")
    private Long cafeId;

    @ApiModelProperty(value = "내용", example = "친구와 함께 가장 가고 싶은 외국 여행지는 어딘가요?")
    private String content;
}
