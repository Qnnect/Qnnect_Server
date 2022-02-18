package com.qnnect.scrap.dtos;

import com.qnnect.questions.domain.Questions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


@ApiModel(value="스크랩,폴더 속 질문 리스트")
@Getter
public class ScrapResponse {

    @ApiModelProperty(value = "스크랩 id", example = "3")
    private Long id;

    private Questions question;
}
