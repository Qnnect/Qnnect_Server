package com.qnnect.diary.dtos;

import com.qnnect.diary.domain.EGroupType;
import com.qnnect.diary.domain.EQuestionCycle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="다이어리 생성")
@Getter
public class DiaryCreateRequest {

    @ApiModelProperty(value = "다이어리 제목", example = "신사고 4인방")
    private String title;

    @ApiModelProperty(value = "함께 하는 사람", example = "친구")
    private EGroupType groupType;

    @ApiModelProperty(value = "질문 주기", example = "7")
    private EQuestionCycle questionCycle;

}
