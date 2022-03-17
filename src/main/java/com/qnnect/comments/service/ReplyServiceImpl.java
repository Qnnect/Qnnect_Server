package com.qnnect.comments.service;

import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.domain.Reply;
import com.qnnect.comments.dtos.ContentDto;
import com.qnnect.comments.repository.CommentRepository;
import com.qnnect.comments.repository.ReplyRepository;
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

    public Reply createReply(Long commentId, String content, User user){
        Comment comment = commentRepository.getById(commentId);
        Reply reply = replyRepository.save(Reply.builder().content(content).user(user)
                .comment(comment).build());
        return reply;
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
