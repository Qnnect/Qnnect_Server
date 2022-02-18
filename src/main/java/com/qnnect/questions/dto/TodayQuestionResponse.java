package com.qnnect.questions.dto;

import com.qnnect.questions.domain.EQuestionerType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value="오늘의 질문")
public class TodayQuestionResponse {

    @ApiModelProperty(value = "오늘의 질문 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "내용", example = "친구와 함께 가장 가고 싶은 외국 여행지는 어딘가요?")
    private String content;

}
