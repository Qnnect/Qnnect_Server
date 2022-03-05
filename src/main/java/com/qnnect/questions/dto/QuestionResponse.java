package com.qnnect.questions.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@ApiModel(value="질문")
public class QuestionResponse {

    @ApiModelProperty(value = "다이어리 질문 리스트 id", example = "1")
    private Long id;

    @ApiModelProperty(value = "생성 날짜")
    private LocalDateTime created;

    @ApiModelProperty(value = "내용", example = "친구와 함께 가장 가고 싶은 외국 여행지는 어딘가요?")
    private String content;
}
