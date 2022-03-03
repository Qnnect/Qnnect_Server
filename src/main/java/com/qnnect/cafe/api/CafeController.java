package com.qnnect.cafe.api;

import com.qnnect.cafe.domain.EQuestionCycle;
import com.qnnect.cafe.dto.CafeCreateRequest;
import com.qnnect.cafe.dto.CafeResponse;
import com.qnnect.cafe.service.CafeServiceImpl;
import com.qnnect.common.CurrentUser;
import com.qnnect.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"다이어리 관련 API"})
public class CafeController {

    private final CafeServiceImpl cafeService;

    @PostMapping("/diaries")
    @ApiOperation(value = "다이어리 생성 api")
    public ResponseEntity<CafeResponse> createCafe(@RequestBody CafeCreateRequest cafeCreateRequest,
                                                   @ApiParam(hidden = true) @CurrentUser User user){
        CafeResponse cafeResponse = cafeService.createCafe(cafeCreateRequest, user);
        return ResponseEntity.ok(cafeResponse);
    }

    @PostMapping("/diaries/{cafeId}")
    @ApiOperation(value = "다이어리 참여 api")
    public ResponseEntity<CafeResponse> joinCafe(@RequestParam String cafeCode,
                                                 @PathVariable long cafeId,
                                                 @ApiParam(hidden = true) @CurrentUser User user){
        CafeResponse cafeResponse = cafeService.joinCafe(cafeCode, user, cafeId);
        return ResponseEntity.ok(cafeResponse);
    }



    @ApiOperation(value = "제목 업데이트 api")
    @PatchMapping("/diaries/{diaryId}/title")
    public ResponseEntity<Void> updateTitle(@RequestParam String title){
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "질문 주기 api")
    @PatchMapping("/diaries/{diaryId}/question-cycle")
    public ResponseEntity<Void> updateQuestionCycle(@RequestParam EQuestionCycle questionCycle){
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "다이어리 삭제 api")
    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long diaryId){
        return ResponseEntity.noContent().build();
    }

}
