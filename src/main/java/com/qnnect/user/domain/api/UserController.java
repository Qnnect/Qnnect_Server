package com.qnnect.user.domain.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 API 정보를 제공"})
public class UserController {

    @ApiOperation(value = "사용자 생성")
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestParam String nickName, String accessToken){
        return ResponseEntity.created(URI.create("/api/v1/user/")).build();
    }

    @ApiOperation(value = "닉네임 수정")
    @PatchMapping("/user/nickname")
    public ResponseEntity<Void> updateNickname(@RequestParam String nickName){
        return ResponseEntity.created(URI.create("/api/v1/user/")).build();
    }

    @ApiOperation(value = "알림 설정")
   @PatchMapping("/user/enablenotification")
    public ResponseEntity<Void> updateNickname(@RequestParam String nickName){
        return ResponseEntity.created(URI.create("/api/v1/user/")).build();
    }

    @ApiOperation(value = "알림 설정")
    정   @PatchMapping("/user/enablenotification")
    public ResponseEntity<Void> updateNickname(@RequestParam String nickName){
        return ResponseEntity.created(URI.create("/api/v1/user/")).build();
    }


}
