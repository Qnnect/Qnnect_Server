package com.qnnect.auth;

import com.qnnect.auth.dto.AuthRequest;
import com.qnnect.auth.dto.AuthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"인증 관련 API"})
public class AuthController {

    private final OAuth2UserService oAuth2UserService;

    @ApiOperation(value = "로그인 및 회원가입 api")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> getTokens(@RequestBody AuthRequest authRequest){
        AuthResponse authResponse = oAuth2UserService.
                signUpOrLogIn(authRequest);
        return ResponseEntity.ok(authResponse);
    }

//    @PostMapping("/reissue")
//    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
//    }
}
