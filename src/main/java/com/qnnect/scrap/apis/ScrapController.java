package com.qnnect.scrap.apis;

import com.qnnect.diary.dtos.DiaryCreateRequest;
import com.qnnect.scrap.dtos.ScrapResponse;
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
@Api(tags = {"스크랩 관련 API"})
public class ScrapController {

    @PostMapping("/users/scrap")
    @ApiOperation(value = "스크랩 하기 api")
    public ResponseEntity<Void> scrapQuestion(
            @PathVariable Long questionId){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/scrap/{scrapId}")
    @ApiOperation(value = "스크랩 취소 api")
    public ResponseEntity<Void> cancelScrapQuestion(
            @PathVariable Long scrapId){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/scrap/")
    @ApiOperation(value = "스크랩 리스트 가져오기 api")
    public ResponseEntity<List<ScrapResponse>> getScrapQuestion(){
        List<ScrapResponse> scrapResponseList = new ArrayList<>();
        return ResponseEntity.ok(scrapResponseList);
    }





}
