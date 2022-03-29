package com.qnnect.comments.service;

import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.domain.Reply;
import com.qnnect.comments.dtos.ContentDto;
import com.qnnect.comments.repository.CommentRepository;
import com.qnnect.comments.repository.ReplyRepository;
import com.qnnect.notification.domain.ENotificationType;
import com.qnnect.notification.domain.Notification;
import com.qnnect.notification.repository.NotificationRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Repository
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;

    public Reply createReply(Long commentId, String content, User user){
        Comment comment = commentRepository.getById(commentId);
        Reply reply = replyRepository.save(Reply.builder().content(content).user(user)
                .comment(comment).build());
        sendReplyNotification(comment.getUser(), reply);
        return reply;
    }

    public void sendReplyNotification(User commentUser, Reply reply) {
        if (commentUser.getId() != reply.getUser().getId()) {
            Notification notification = Notification.builder().notificationType(ENotificationType.reply)
                    .contentId(reply.getComment().getId()).user(commentUser).content(reply.getContent())
                    .senderName(reply.getUser().getNickName())
                    .groupName(reply.getComment().getCafeQuestion().getCafe().getTitle()).build();
            notificationRepository.save(notification);
        }
    }

    public void updateReply(Long replyId, String content, User user){
        Reply reply = replyRepository.getById(replyId);
        if(content != null){
            reply.setContent(content);
        }
        replyRepository.save(reply);
    }

    public void deleteReply(Long replyId, User user){
        replyRepository.deleteById(replyId);
    }
}
