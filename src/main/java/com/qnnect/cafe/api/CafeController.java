package com.qnnect.cafe.api;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.cafe.domain.EQuestionCycle;
import com.qnnect.cafe.dto.CafeCreateRequest;
import com.qnnect.cafe.dto.CafeDetailResponse;
import com.qnnect.cafe.service.CafeService;
import com.qnnect.common.CurrentUser;
import com.qnnect.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"카페 API"})
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("/cafes")
    @ApiOperation(value = "카페 생성 api")
    public ResponseEntity<Long> createCafe(@RequestBody CafeCreateRequest cafeCreateRequest,
                                                         @ApiIgnore @CurrentUser User user){
        Cafe cafe = cafeService.createCafe(cafeCreateRequest, user);
        return ResponseEntity.ok(cafe.getId());
    }

    @PostMapping("/cafes/{cafeId}")
    @ApiOperation(value = "카페 참여 api")
    public ResponseEntity<CafeDetailResponse> joinCafe(@RequestParam String cafeCode,
                                                       @PathVariable long cafeId,
                                                       @ApiIgnore @CurrentUser User user){
        CafeDetailResponse cafeResponse = cafeService.joinCafe(cafeCode, user, cafeId);
        return ResponseEntity.ok(cafeResponse);
    }

    @GetMapping("/cafes/{cafeId}")
    @ApiOperation(value = "카페 홈 api")
    public ResponseEntity<CafeDetailResponse> showCafe(@PathVariable long cafeId,
                                                       @ApiIgnore @CurrentUser User user){
        CafeDetailResponse cafeResponse = cafeService.getCafe(cafeId, user);
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
