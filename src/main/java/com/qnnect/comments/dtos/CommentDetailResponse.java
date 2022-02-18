package com.qnnect.comments.dtos;

import com.qnnect.user.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@ApiModel(value="댓글(대댓글 포함)")
@Getter
public class CommentDetailResponse {

    @ApiModelProperty(value = "댓글", example = "지민이는 저번부터 싱가폴 얘기하더니 " +
            "진짜 가고싶나보네ㅋㅋㅋ")
    private String content;

//    @ApiModelProperty(value = "댓글", example = "지민이는 저번부터 싱가폴 얘기하더니 " +
//            "진짜 가고싶나보네ㅋㅋㅋ")
    private User user;


    private List<ReplyResponse> replies;


}
