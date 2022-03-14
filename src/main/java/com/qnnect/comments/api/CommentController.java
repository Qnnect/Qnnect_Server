package com.qnnect.comments.api;

import com.qnnect.comments.domain.Comment;
import com.qnnect.comments.dtos.CommentDetailResponse;
import com.qnnect.comments.service.CommentService;
import com.qnnect.common.CurrentUser;
import com.qnnect.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"댓글 관련 API"})
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/cafes/{cafeId}/questions/{cafeQuestionId}/comments")
    @ApiOperation(value = "댓글 생성 api")
    public ResponseEntity<Long> createComment(@PathVariable Long cafeId,
                                              @PathVariable Long cafeQuestionId,
                                              @ApiIgnore @CurrentUser User user,
                                              @RequestPart(required = true) String content,
                                              @RequestPart(required = false) MultipartFile image1,
                                              @RequestPart(required = false) MultipartFile image2,
                                              @RequestPart(required = false) MultipartFile image3,
                                              @RequestPart(required = false) MultipartFile image4,
                                              @RequestPart(required = false) MultipartFile image5
                                              ){

        Long commentId = commentService.create(cafeQuestionId, user, content, image1,
                image2, image3, image4, image5);
        return ResponseEntity.ok(commentId);
    }

    @PatchMapping("/comments/{commentId}")
    @ApiOperation(value = "댓글 수정 api")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                              @ApiIgnore @CurrentUser User user,
                                              @RequestPart(required = true) String content,
                                              @RequestPart(required = false) MultipartFile image1,
                                              @RequestPart(required = false) MultipartFile image2,
                                              @RequestPart(required = false) MultipartFile image3,
                                              @RequestPart(required = false) MultipartFile image4,
                                              @RequestPart(required = false) MultipartFile image5){

        commentService.update(commentId, user, content, image1,
                image2, image3, image4, image5);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/comments/{commentId}")
    @ApiOperation(value = "댓글 삭제 api")
    public ResponseEntity<Void> deleteComment(
                                              @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments/{commentId}")
    @ApiOperation(value = "댓글 가져오기 api(대댓글 포함)")
    public ResponseEntity<CommentDetailResponse> getComment(@PathVariable Long commentId,
                                                            @ApiIgnore @CurrentUser User user){
        CommentDetailResponse commentDetailResponse = commentService.getComment(commentId, user);
        return ResponseEntity.ok(commentDetailResponse);
    }

}
