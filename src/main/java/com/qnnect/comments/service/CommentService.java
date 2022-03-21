package com.qnnect.comments.service;

import com.qnnect.comments.dtos.CommentDetailResponse;
import com.qnnect.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommentService {
    Long create(Long cafeQuestionId, User user, String content, MultipartFile image1,
                MultipartFile image2, MultipartFile image3, MultipartFile imge4,
                MultipartFile image5);
    void deleteComment(Long commentId);

    CommentDetailResponse getComment(Long commentId, User user);

    void update(Long commentId, User user, String content, MultipartFile image1,
                       MultipartFile image2, MultipartFile image3, MultipartFile image4,
                       MultipartFile image5);
}
