package com.qnnect.questions.apis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 질문 생성관련 API"})
public class UserQuestionController {
    @PostMapping("/diaries/{diaryId}/question/")
    @ApiOperation(value = "사용자 질문 생성 api(바로보이게)")
    public ResponseEntity<Void> createQuestionNow(@RequestBody String question){//바로 보이게
        return ResponseEntity.ok().build();
    }

    @PostMapping("/diaries/{diaryId}/question/list")
    @ApiOperation(value = "사용자 질문 생성 api(질문리스트로)")
    public ResponseEntity<Void> createQuestionLater(@RequestBody String question){//나중에
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}/question/{questionId}")
    @ApiOperation(value = "사용자 질문 수정 api")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long questionId,@RequestBody String question){//나중에
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/diaries/{diaryId}/question/{questionId}")
    @ApiOperation(value = "사용자 질문 삭제 api")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId){//나중에
        return ResponseEntity.noContent().build();
    }
}
