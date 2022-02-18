package com.qnnect.diary.apis;

import com.qnnect.diary.domain.EQuestionCycle;
import com.qnnect.diary.dtos.DiaryCreateRequest;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DiaryController {

    @PostMapping("/diaries")
    public ResponseEntity<Void> createDiary(@RequestBody DiaryCreateRequest diaryCreateRequest){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/diaries/{diaryId}/drinks")
    public ResponseEntity<Void> addDrink(@RequestParam String Drink, @PathVariable Long diaryId){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}/title")
    public ResponseEntity<Void> updateTitle(@RequestParam String title){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/diaries/{diaryId}/question-cycle")
    public ResponseEntity<Void> updateQuestionCycle(@RequestParam EQuestionCycle questionCycle){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long diaryId){
        return ResponseEntity.noContent().build();
    }

}
