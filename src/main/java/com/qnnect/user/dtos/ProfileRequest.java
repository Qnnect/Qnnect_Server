package com.qnnect.user.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="프로필 조회")
@Getter
public class ProfileRequest {
    @ApiModelProperty(value = "이미지")
    private String image;

    @ApiModelProperty(value = "닉네임", example = "orange")
    private String nickName;
}
