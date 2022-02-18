package com.qnnect.comments.apis;

import com.qnnect.comments.dtos.CommentDetailResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"대댓글 관련 API"})
public class ReplyController {
    @GetMapping("/diaries/{diaryId}/question/{questionId}/comments/reply")
    @ApiOperation(value = "대댓글 생성 api")
    public ResponseEntity<Void> createReply(@PathVariable Long diaryId,
                                              @PathVariable Long questionId ,
                                              @RequestBody String reply){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}/question/{questionId}/comments/{commentId}/reply" +
            "/{replyId}")
    @ApiOperation(value = "대댓글 수정 api")
    public ResponseEntity<Void> updateReply(@PathVariable Long diaryId,
                                              @PathVariable Long questionId,
                                              @PathVariable Long commentId,
                                              @PathVariable Long replyId,
                                              @RequestBody String comment){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/diaries/{diaryId}/question/{questionId}/comments/{commentId}/reply" +
            "/{replyId}")
    @ApiOperation(value = "대댓글 삭제 api")
    public ResponseEntity<Void> deleteComment(@PathVariable Long diaryId,
                                              @PathVariable Long questionId,
                                              @PathVariable Long commentId,
                                              @PathVariable Long replyId,
                                              @RequestBody String comment){
        return ResponseEntity.noContent().build();
    }
}
