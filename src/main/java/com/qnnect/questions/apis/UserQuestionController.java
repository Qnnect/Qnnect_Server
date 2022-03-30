package com.qnnect.questions.apis;

import com.qnnect.common.CurrentUser;
import com.qnnect.questions.domain.Question;
import com.qnnect.questions.dto.WaitingListQuestionResponse;
import com.qnnect.questions.service.CafeQuestionService;
import com.qnnect.questions.service.QuestionService;
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
    private final QuestionService questionService;

    @PostMapping("/cafes/{cafeId}/question/")
    @ApiOperation(value = "사용자 질문 생성 api")
    public ResponseEntity<Long> createQuestion(@PathVariable Long cafeId, @RequestBody String content, @ApiIgnore @CurrentUser User user){
        Long questionId = cafeQuestionService.create(cafeId, content,user );
        return ResponseEntity.ok(questionId);
    }

    @PatchMapping("/question/{cafeQuestionId}")
    @ApiOperation(value = "사용자 질문 수정 api")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long cafeQuestionId,@RequestBody String content){
        cafeQuestionService.update(cafeQuestionId, content);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/question/{cafeQuestionId}")
    @ApiOperation(value = "사용자 질문 삭제 api")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long cafeQuestionId){
        cafeQuestionService.delete(cafeQuestionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("my/question/{questionId}")
    @ApiOperation(value = "생성한 질문 가져오기 api")
    public ResponseEntity<WaitingListQuestionResponse> getQuestion(@PathVariable Long questionId){
        WaitingListQuestionResponse questionDetail = questionService.get(questionId);
        return ResponseEntity.ok(questionDetail);
    }
}
