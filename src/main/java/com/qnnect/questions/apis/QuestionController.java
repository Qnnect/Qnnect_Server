package com.qnnect.questions.apis;


import com.qnnect.common.CurrentUser;
import com.qnnect.questions.dto.QuestionDetailResponse;
import com.qnnect.questions.dto.QuestionMainResponse;
import com.qnnect.questions.dto.TodayQuestionResponse;
import com.qnnect.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"질문 관련 API"})
public class QuestionController {


//    @GetMapping("/diaries/{diaryId}/question/all")
//    @ApiOperation(value = "전체 질문")
//    public ResponseEntity<List<QuestionMainResponse>> getQuestionAll(@PathVariable Long diaryId){
//        List<QuestionMainResponse> questionResponse = new ArrayList<>();
//        return ResponseEntity.ok(questionResponse);
//    }

    @GetMapping("/question/{cafeQuestionId}")
    @ApiOperation(value = "질문 가져오기(댓글 함께)")
    public ResponseEntity<QuestionDetailResponse> getQuestion(@PathVariable Long cafeQuestionId
            , @ApiIgnore @CurrentUser User user){
        QuestionDetailResponse questionDetailResposeResponse = new QuestionDetailResponse();
        return ResponseEntity.ok(questionDetailResposeResponse);
    }


}
