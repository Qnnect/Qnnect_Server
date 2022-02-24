package com.qnnect.auth;

import com.qnnect.auth.dto.AuthRequest;
import com.qnnect.auth.dto.AuthResponse;
import com.qnnect.auth.dto.TokenReissue;
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

    private final AuthUserService authUserService;

    @ApiOperation(value = "로그인 및 회원가입 api")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> getTokens(@RequestBody AuthRequest authRequest){
        AuthResponse authResponse = authUserService.signUpOrLogIn(authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @ApiOperation(value= "토큰 재발급")
    @PostMapping("/reissue")
    public ResponseEntity<TokenReissue> reissue(@RequestBody TokenReissue tokenReissueRequest) {
        TokenReissue tokenReissueResponse = authUserService.reissue(tokenReissueRequest);
        return ResponseEntity.ok(tokenReissueResponse);
    }
}
