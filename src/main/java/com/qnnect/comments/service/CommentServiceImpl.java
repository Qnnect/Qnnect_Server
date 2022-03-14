package com.qnnect.comments.service;

import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.domain.Reply;
import com.qnnect.comments.dtos.CommentDetailResponse;
import com.qnnect.comments.repository.CommentRepository;
import com.qnnect.comments.repository.ReplyRepository;
import com.qnnect.common.S3Uploader;
import com.qnnect.common.exception.CustomException;
import com.qnnect.common.exception.ErrorCode;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.dto.CafeQuestionResponse;
import com.qnnect.questions.repository.CafeQuestionRepository;
import com.qnnect.user.domain.User;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.qnnect.common.exception.ErrorCode.CAFE_QUESTION_NOT_FOUND;
import static com.qnnect.common.exception.ErrorCode.COMMENT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final S3Uploader s3Uploader;
    private final CafeQuestionRepository cafeQuestionRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    @Override
    @Transactional
    public Long create(Long cafeQuestionId, User user, String content, MultipartFile image1,
                       MultipartFile image2, MultipartFile image3, MultipartFile image4,
                       MultipartFile image5) {
        String imageUrl1 = null;
        String imageUrl2 = null;
        String imageUrl3 = null;
        String imageUrl4 = null;
        String imageUrl5 = null;

        System.out.println(cafeQuestionId);
        CafeQuestion cafeQuestion = cafeQuestionRepository.findById(cafeQuestionId)
                .orElseThrow(() -> new CustomException(CAFE_QUESTION_NOT_FOUND));
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(cafeQuestion.getCreatedAt().plusDays(7))){
            throw new CustomException(ErrorCode.CAFE_QUESTION_DATE_PASSED);
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

        user.addPoint(5);
        userRepository.save(user);

        return comment.getId();
    }

    @Transactional
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


    @Override
    @Transactional
    public CommentDetailResponse getComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CustomException(COMMENT_NOT_FOUND));
        List<Reply> reply = replyRepository.findAllByComment_Id(comment.getId());
        return CommentDetailResponse.from(comment, user, reply);
    }

    @Override
    @Transactional
    public void update(Long commentId, User user, String content, MultipartFile image1,
                       MultipartFile image2, MultipartFile image3, MultipartFile image4,
                       MultipartFile image5) {
        String imageUrl1 = null;
        String imageUrl2 = null;
        String imageUrl3 = null;
        String imageUrl4 = null;
        String imageUrl5 = null;


        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));

        comment.setContent(content);
        if (comment.getImageUrl1() != null) {
            s3Uploader.deleteS3(comment.getImageUrl1(), "comment");
            if(image1!=null){
                imageUrl1 = uploadImage(image1);
                comment.setImageUrl1(imageUrl1);
            }else{
                comment.setImageUrl1(null);
            }
        }
        if (comment.getImageUrl2() != null) {
            s3Uploader.deleteS3(comment.getImageUrl2(), "comment");
            if(image2!=null){
                imageUrl2 = uploadImage(image2);
                comment.setImageUrl2(imageUrl2);
            }else{
                comment.setImageUrl2(null);
            }
        }
        if (comment.getImageUrl3() != null) {
            log.info("image not null");
            s3Uploader.deleteS3(comment.getImageUrl3(), "comment");
            if(image3!=null){
                imageUrl3 = uploadImage(image3);
                comment.setImageUrl3(imageUrl3);
            }else{
                comment.setImageUrl3(null);
            }
        }
        if (comment.getImageUrl4() != null) {
            s3Uploader.deleteS3(comment.getImageUrl4(), "comment");
            if(image4!=null){
                imageUrl4 = uploadImage(image4);
                comment.setImageUrl4(imageUrl4);
            }else{
                comment.setImageUrl4(null);
            }
        }
        if (comment.getImageUrl5() != null) {
            s3Uploader.deleteS3(comment.getImageUrl5(), "comment");
            if(image5!=null){
                imageUrl5 = uploadImage(image5);
                comment.setImageUrl5(imageUrl5);
            }else{
                comment.setImageUrl5(null);
            }
        }

        commentRepository.save(comment);
    }
}
