package com.qnnect.questions.apis;

import com.qnnect.common.CurrentUser;
import com.qnnect.questions.dto.QuestionCreateRequest;
import com.qnnect.questions.service.CafeQuestionService;
import com.qnnect.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 질문 생성관련 API"})
public class UserQuestionController {

    private final CafeQuestionService cafeQuestionService;

    @PostMapping("/diaries/{diaryId}/question/")
    @ApiOperation(value = "사용자 질문 생성 api")
    public ResponseEntity<Void> createQuestion(@RequestBody QuestionCreateRequest questionCreateRequest, @ApiIgnore @CurrentUser User user){
        cafeQuestionService.create(questionCreateRequest,user );
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
