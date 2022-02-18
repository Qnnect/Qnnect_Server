package com.qnnect.scrap.dtos;

import com.qnnect.questions.domain.Questions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class FolderListResponse {

    @ApiModelProperty(value = "폴더 id", example = "3")
    private Long id;

    @ApiModelProperty(value = "폴더 이름", example = "3")
    private String folderName;
}
