package com.qnnect.diary.apis;

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
@Api(tags = {"다이어리 관련 API"})
public class DiaryController {

    @PostMapping("/diaries")
    @ApiOperation(value = "다이어리 생성 api")
    public ResponseEntity<Void> createDiary(@RequestBody DiaryCreateRequest diaryCreateRequest){
        return ResponseEntity.ok().build();
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
