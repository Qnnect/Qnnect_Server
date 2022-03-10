package com.qnnect.comments.service;

import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.repository.CommentRepository;
import com.qnnect.common.S3Uploader;
import com.qnnect.common.exception.cafe.CafeQuestionNotFoundException;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;
    private final CafeQuestionRepository cafeQuestionRepository;

    public Long create(Long cafeQuestionId, User user, String content, MultipartFile image1,
                       MultipartFile image2, MultipartFile image3, MultipartFile image4,
                       MultipartFile image5) {
        String imageUrl1 = null;
        String imageUrl2 = null;
        String imageUrl3 = null;
        String imageUrl4 = null;
        String imageUrl5 = null;

        CafeQuestion cafeQuestion = cafeQuestionRepository.findById(cafeQuestionId)
                .orElseThrow(CafeQuestionNotFoundException::new);
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(cafeQuestion.getCreatedAt().plusDays(7))){
            throw new CafeQuestionNotFoundException();
        }
        if (image1 != null) {
            imageUrl1 = uploadImage(image1);
        }
        if (image2 != null) {
            imageUrl2 = uploadImage(image2);
        }
        if (image3 != null) {
            imageUrl3 = uploadImage(image3);
        }
        if (image4 != null) {
            imageUrl4 = uploadImage(image4);
        }
        if (image5 != null) {
            imageUrl5 = uploadImage(image5);
        }

        Comment comment = commentRepository.save(Comment.builder().content(content)
                .imageUrl1(imageUrl1).imageUrl2(imageUrl2).imageUrl3(imageUrl3)
                .imageUrl4(imageUrl4).imageUrl5(imageUrl5).user(user)
                .cafeQuestion(cafeQuestion).build());

        return comment.getId();
    }

    public String uploadImage(MultipartFile image) {
        try {
            String imageUrl = s3Uploader.upload(image, "comment");
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Transactional
    @Override
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
