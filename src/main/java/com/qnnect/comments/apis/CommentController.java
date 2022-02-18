package com.qnnect.comments.apis;

import com.qnnect.diary.domain.EQuestionCycle;
import com.qnnect.diary.dtos.DiaryCreateRequest;
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
    @PostMapping("/diaries/{diaryId}/question/comments")
    @ApiOperation(value = "댓글 생성 api")
    public ResponseEntity<Void> createDiary(@RequestBody DiaryCreateRequest diaryCreateRequest){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/comments/{}")
    public ResponseEntity<Void> updateTitle(@RequestParam String title){
        return ResponseEntity.ok().build();
    }


}
