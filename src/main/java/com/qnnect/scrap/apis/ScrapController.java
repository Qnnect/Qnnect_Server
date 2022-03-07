package com.qnnect.scrap.apis;

import com.qnnect.common.CurrentUser;
import com.qnnect.scrap.dtos.ScrapResponse;
import com.qnnect.scrap.service.ScrapService;
import com.qnnect.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"스크랩 관련 API"})
public class ScrapController {

    private final ScrapService scrapService;

    @PostMapping("/users/scrap")
    @ApiOperation(value = "스크랩 하기 api")
    public ResponseEntity<Void> scrapQuestion(
            @RequestParam Long cafeQuestionId,@ApiIgnore @CurrentUser User user){
        scrapService.addScrap(user, cafeQuestionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/scrap")
    @ApiOperation(value = "스크랩 취소 api")
    public ResponseEntity<Void> deleteScrapQuestion(
            @RequestParam Long cafeQuestionId, @ApiIgnore @CurrentUser User user){
        System.out.println("controller");
        scrapService.deleteScrap(user, cafeQuestionId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/users/scrap/")
//    @ApiOperation(value = "전체 스크랩 리스트 가져오기 api")
//    public ResponseEntity<List<ScrapResponse>> getAllScrapQuestion(@ApiIgnore @CurrentUser User user){
//        List<ScrapResponse> scrapResponseList = scrapService.getAllScraps(user);
//        return ResponseEntity.ok(scrapResponseList);
//    }

}
