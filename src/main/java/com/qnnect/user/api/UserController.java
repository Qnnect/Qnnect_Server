package com.qnnect.user.domain.api;

import com.qnnect.auth.domain.ELoginType;
import com.qnnect.auth.domain.TokenResponse;
import com.qnnect.user.dtos.ProfileUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 API 정보를 제공"})
public class UserController {

    @ApiOperation(value = "사용자 생성")
    @PostMapping("/user/{accessToken}")
    public ResponseEntity<TokenResponse> createUser(@PathVariable String accessToken, ELoginType loginType){
        TokenResponse tokenResponse = new TokenResponse();
        return ResponseEntity.ok(tokenResponse);
    }

    @ApiOperation(value = "프로필 변경")
    @PatchMapping("/user/nickname")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest){
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "알림 설정")
    @PatchMapping("/user/enablenotification")
    public ResponseEntity<Void> enableNotification (@RequestParam String nickName){
        return ResponseEntity.ok().build();
    }

//    @ApiOperation(value = "알림 설정")
//      @PatchMapping("/user/enablenotification")
//    public ResponseEntity<Void> updateNickname(@RequestParam String nickName){
//        return ResponseEntity.created(URI.create("/api/v1/user/")).build();
//    }


}
