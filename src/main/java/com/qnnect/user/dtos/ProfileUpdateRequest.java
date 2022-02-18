package com.qnnect.user.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@ApiModel(value="프로필 업데이트")
@Getter
public class ProfileUpdateRequest {

    @ApiModelProperty(value = "이미지")
    private MultipartFile image;

    @ApiModelProperty(value = "닉네임", example = "orange")
    private String nickName;
}
