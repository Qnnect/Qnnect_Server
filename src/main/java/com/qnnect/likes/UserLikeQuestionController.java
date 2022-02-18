package com.qnnect.likes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"좋아요 API"})
public class UserLikeQuestionController {
    @PostMapping("/users/question/{questionId}")
    @ApiOperation(value = "좋아요 하기 api")
    public ResponseEntity<Void> likeQuestion(
            @PathVariable Long questionId){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/question/{scrapId}")
    @ApiOperation(value = "좋아요 취소 api")
    public ResponseEntity<Void> cancelScrapQuestion(
            @PathVariable Long scrapId){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/question/like")
    @ApiOperation(value = "좋아요 많이 받은 질문 api")
    public ResponseEntity<List<LikeRankingResponse>> rankingQuestion(){
        List<LikeRankingResponse> likeRankingResponses = new ArrayList<>();
        return ResponseEntity.ok(likeRankingResponses);
    }
}
