package com.qnnect.user.dtos;

import com.qnnect.user.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponse {

    @ApiModelProperty(value = "이미지")
    private String image;

    @ApiModelProperty(value = "닉네임", example = "orange")
    private String nickName;

    public static ProfileResponse from(User user){
        return new ProfileResponse(user.getProfilePicture(),user.getNickName());
    }
}
