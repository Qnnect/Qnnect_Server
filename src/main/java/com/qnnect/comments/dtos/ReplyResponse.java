package com.qnnect.comments.dtos;

import com.qnnect.user.domain.User;
import io.swagger.annotations.ApiModelProperty;

public class ReplyResponse {

    @ApiModelProperty(value = "댓글", example = "지민이는 저번부터 싱가폴 얘기하더니 " +
            "진짜 가고싶나보네ㅋㅋㅋ")
    private String content;

    private User user;
}
