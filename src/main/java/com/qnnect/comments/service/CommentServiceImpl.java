package com.qnnect.comments.service;

import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.domain.Reply;
import com.qnnect.comments.dtos.CommentDetailResponse;
import com.qnnect.comments.repository.CommentRepository;
import com.qnnect.comments.repository.ReplyRepository;
import com.qnnect.common.S3Uploader;
import com.qnnect.common.exception.CustomException;
import com.qnnect.common.exception.ErrorCode;
//import com.qnnect.notification.FirebaseCloudMessageService;
import com.qnnect.notification.domain.FcmToken;
import com.qnnect.notification.repository.FcmTokenRepository;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.questions.domain.EQuestionerType;
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
//    private final FirebaseCloudMessageService firebaseCloudMessageService;
    private final FcmTokenRepository fcmTokenRepository;

    @Override
    @Transactional
    public Long create(Long cafeQuestionId, User user, String content, MultipartFile image1,
                       MultipartFile image2, MultipartFile image3, MultipartFile image4,
                       MultipartFile image5) {
        String [] image = new String[5];

        System.out.println(cafeQuestionId);
        CafeQuestion cafeQuestion = cafeQuestionRepository.findById(cafeQuestionId)
                .orElseThrow(() -> new CustomException(CAFE_QUESTION_NOT_FOUND));
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(cafeQuestion.getCreatedAt().plusDays(7))){
            throw new CustomException(ErrorCode.CAFE_QUESTION_DATE_PASSED);
        }

        image = imageUploader(image1,image2, image3, image4, image5, image);

        Comment comment = commentRepository.save(Comment.builder().content(content)
                .imageUrl1(image[0]).imageUrl2(image[1]).imageUrl3(image[2])
                .imageUrl4(image[3]).imageUrl5(image[4]).user(user)
                .cafeQuestion(cafeQuestion).build());

        user.addPoint(5);
        userRepository.save(user);

        if(cafeQuestion.getQuestions().getQuestionerType() == EQuestionerType.user){
            sendCommentNotification(cafeQuestion.getQuestions().getUser(), comment);
        }
        return comment.getId();
    }

    public void sendCommentNotification(User user, Comment comment){
//        FcmToken fcmToken = fcmTokenRepository.findByUserId(user.getId())
////                .orElseThrow(()-> new CustomException(ErrorCode.INVALID_AUTH_TOKEN));
//        try{
//            firebaseCloudMessageService.sendMessageTo(
//                    "eVsvDZAkrEvamfY90tzPPq:APA91bEIRRKC_oZdj8lcxsJVW3vr112tVV_Hz5_9S9JL234gDaLMcld77yYXTy_ybhHUxAJKTjTT05IvnFQYGK6oGQfBO70ees2gqiEtOQgauP9S_8aQtth4uyCLN3PjBVztcF6-fYTB",
//                    "📮내 답변에 댓글이 달렸어요! 댓글을 보러 가볼까요?",
//                    comment.getContent());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public String[] imageUploader(MultipartFile image1, MultipartFile image2,
                                 MultipartFile image3, MultipartFile image4,
                                 MultipartFile image5, String[] image){
        if (image1 != null) {
            image[0] = uploadImage(image1);
        }
        if (image2 != null) {
            image[1] = uploadImage(image2);
        }
        if (image3 != null) {
            image[2] = uploadImage(image3);
        }
        if (image4 != null) {
            image[3] = uploadImage(image4);
        }
        if (image5 != null) {
            image[4] = uploadImage(image5);
        }
        return image;
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
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CustomException(COMMENT_NOT_FOUND));
        imageDeleter(comment);
        commentRepository.deleteById(commentId);
    }

    public void imageDeleter(Comment comment){
        if (comment.getImageUrl1() != null) {
            s3Uploader.deleteS3(comment.getImageUrl1(), "comment");
        }
        if (comment.getImageUrl2() != null) {
            s3Uploader.deleteS3(comment.getImageUrl2(), "comment");
        }
        if (comment.getImageUrl3() != null) {
            s3Uploader.deleteS3(comment.getImageUrl3(), "comment");
        }
        if (comment.getImageUrl4() != null) {
            s3Uploader.deleteS3(comment.getImageUrl4(), "comment");
        }
        if (comment.getImageUrl5() != null) {
            s3Uploader.deleteS3(comment.getImageUrl5(), "comment");
        }
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

        String imageUrl[] = new String[5];


        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));

        comment.setContent(content);
        imageDeleter(comment);
        imageUrl = imageUploader(image1, image2,image3,image4,image5,imageUrl);
        comment.setImageUrl1(imageUrl[0]);
        comment.setImageUrl2(imageUrl[1]);
        comment.setImageUrl3(imageUrl[2]);
        comment.setImageUrl4(imageUrl[3]);
        comment.setImageUrl5(imageUrl[4]);

        commentRepository.save(comment);
    }
}
