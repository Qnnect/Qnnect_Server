package com.qnnect.cafe.api;

import com.qnnect.cafe.dto.DiaryMemberDrinks;
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
@Api(tags ="다이어리 사용자의 음료 관련 API")
public class DiaryDrinkController {
    @ApiOperation(value = "다이어리 음료 선택 api")
    @PostMapping("/diaries/{diaryId}/drinks")
    public ResponseEntity<Void> addDrink(@RequestParam String Drink, @PathVariable Long diaryId){
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "다이어리 음료 보기 api")
    @GetMapping("/diaries/{diaryId}/drinks")
    public ResponseEntity<List<DiaryMemberDrinks>> getDrink(@PathVariable Long diaryId){
        List<DiaryMemberDrinks> diaryMemberDrinksList = new ArrayList<>();
        return ResponseEntity.ok(diaryMemberDrinksList);
    }
}
