package com.qnnect.scrap.apis;

import com.qnnect.scrap.dtos.FolderListResponse;
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
@Api(tags = {"폴더 관련 API"})
public class FolderController {

    @PostMapping("/users/scraps/folder")
    @ApiOperation(value = "폴더 생성 api")
    public ResponseEntity<Void> createFolder(
            @RequestParam String folderName){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/scraps/folder")
    @ApiOperation(value = "폴더 리스트 가져오기 api")
    public ResponseEntity<List<FolderListResponse>> getFolderList(){
        List<FolderListResponse> folderListResponses = new ArrayList<>();
        return ResponseEntity.ok(folderListResponses);
    }

    @DeleteMapping("/users/scraps/folder/{folderId}")
    @ApiOperation(value = "폴더 삭제 api")
    public ResponseEntity<Void> deleteFolder(
            @RequestParam Long folderId){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/scraps/folder/{folderId}")
    @ApiOperation(value = "폴더 하나 불러오기 api")
    public ResponseEntity<List<ScrapResponse>> getFolder(
            @PathVariable Long folderId){
        List<ScrapResponse> scrapResponseList = new ArrayList<>();
        return ResponseEntity.ok(scrapResponseList);
    }

    @PostMapping("/users/scraps/folder/{folderId}")
    @ApiOperation(value = "폴더에 질문 추가 api")
    public ResponseEntity<List<ScrapResponse>> addQuestionToFolder(
            @PathVariable Long folderId){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/scraps/folder/{folderId}")
    @ApiOperation(value = "폴더에 질문 삭제 api")
    public ResponseEntity<List<ScrapResponse>> removeQuestionFromFolder(
            @PathVariable Long folderId){
        return ResponseEntity.ok().build();
    }

}
