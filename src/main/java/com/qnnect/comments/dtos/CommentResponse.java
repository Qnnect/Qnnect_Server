package com.qnnect.comments.dtos;

import com.qnnect.user.dtos.ProfileResponse;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponse {
    LocalDate createdAt;
    ProfileResponse profileResponse;
    String content;
    String imageUrl1;
    String imageUrl2;
    String imageUrl3;
    String imageUrl4;
    String imageUrl5;
    int replyCount;


}
