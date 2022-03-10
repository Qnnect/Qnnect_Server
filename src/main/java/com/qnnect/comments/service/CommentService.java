package com.qnnect.comments.service;

import com.qnnect.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface CommentService {
    Long create(Long cafeQuestionId, User user, String content, MultipartFile image1,
                MultipartFile image2, MultipartFile image3, MultipartFile imge4,
                MultipartFile image5);
    void deleteComment(Long commentId);
}
