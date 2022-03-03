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
@Api(tags = {"댓글 관련 API"})
public class CommentController {
    @PostMapping("/diaries/{diaryId}/question/{questionId}/comments")
    @ApiOperation(value = "댓글 생성 api")
    public ResponseEntity<Void> createComment(@PathVariable Long diaryId,
                                              @PathVariable Long questionId ,
                                              @RequestBody String comment){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}/question/{questionId}/comments/{commentId}")
    @ApiOperation(value = "댓글 수정 api")
    public ResponseEntity<Void> updateComment(@PathVariable Long diaryId,
                                              @PathVariable Long questionId,
                                              @PathVariable Long commentId,
                                              @RequestBody String comment){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/diaries/{diaryId}/question/{questionId}/comments/{commentId}")
    @ApiOperation(value = "댓글 삭제 api")
    public ResponseEntity<Void> deleteComment(@PathVariable Long diaryId,
                                              @PathVariable Long questionId,
                                              @PathVariable Long commentId,
                                              @RequestBody String comment){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/diaries/{diaryId}/question/{questionId}/comments/{commentId}")
    @ApiOperation(value = "댓글 가져오기 api(대댓글 포함")
    public ResponseEntity<CommentDetailResponse> getComment(@PathVariable Long diaryId,
                                                            @PathVariable Long questionId,
                                                            @PathVariable Long commentId,
                                                            @RequestBody String comment){
        CommentDetailResponse commentDetailResponse = new CommentDetailResponse();
        return ResponseEntity.ok(commentDetailResponse);
    }

}
