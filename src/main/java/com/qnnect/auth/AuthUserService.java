package com.qnnect.auth;

import com.qnnect.auth.client.ClientApple;
import com.qnnect.auth.client.ClientKakao;
import com.qnnect.auth.dto.AuthRequest;
import com.qnnect.auth.dto.TokenReissue;
import com.qnnect.auth.token.*;
import com.qnnect.auth.dto.AuthResponse;
import com.qnnect.user.domain.User;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final ClientKakao clientKakao;
    private final ClientApple clientApple;
    private final RefreshTokenRepository refreshTokenRepository;
    private RefreshToken refreshToken;

    public AuthResponse signUpOrLogIn(AuthRequest authRequest) {
        User user;
        if (authRequest.getLoginType() == ELoginType.kakao) {
            user = getKakaoProfile(authRequest.getAccessToken());
        } else {
            user = getAppleProfile(authRequest.getAccessToken());
        }

        Token token = tokenService.generateToken(user.getSocialId(), "USER");
        boolean isNewMember = false;

        if (userRepository.findBySocialId(user.getSocialId()).equals(Optional.empty())) {
            userRepository.save(user);
            System.out.println(user.getSocialId());
            refreshToken = RefreshToken.builder()
                    .id(user.getSocialId())
                    .refreshToken(token.getRefreshToken())
                    .build();
            refreshTokenRepository.save(refreshToken);
            isNewMember = true;
        } else {
            log.debug("user exists");
            Optional<RefreshToken> oldRefreshToken = refreshTokenRepository.findById(user.getSocialId());
            if (!oldRefreshToken.equals(Optional.empty())) {
                refreshToken = refreshToken.updateValue(token.getRefreshToken());
            } else {
                refreshToken = RefreshToken.builder()
                        .id(user.getSocialId())
                        .refreshToken(token.getRefreshToken())
                        .build();
            } refreshTokenRepository.save(refreshToken);

        }

        //token return 시키기
        return AuthResponse.builder()
                .isNewMember(isNewMember)
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .userSettingDone(false)
                .build();
    }

    public User getKakaoProfile(String oauthToken) {
        User kakaoUser = clientKakao.getUserData(oauthToken);
        System.out.println("getKakao");
        return kakaoUser;
    }

    public User getAppleProfile(String oauthToken){
        User appleUser = clientApple.getUserData(oauthToken);
        System.out.println("getApple");
        return appleUser;
    }

    public TokenReissue reissue(TokenReissue tokenReissueRequest) {
        Long timeLeft  = tokenService.verifyRefreshToken(tokenReissueRequest.getRefreshToken());
        if ( timeLeft == 0) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }
        String currentSocialId = tokenService.getSocialId(tokenReissueRequest.getAccessToken());
        System.out.println("refreshToken:" + tokenReissueRequest.getRefreshToken());
        System.out.println("refreshToken " + refreshToken.getToken());

        RefreshToken refreshToken = refreshTokenRepository.findById(currentSocialId)
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        if (!refreshToken.getToken().equals(tokenReissueRequest.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        Token token = tokenService.generateToken(currentSocialId, "USER");
        TokenReissue tokenReissueResponse;
        if(timeLeft < 1000L * 60L * 60L * 24L * 3L){

            refreshToken = refreshToken.updateValue(token.getRefreshToken());
            refreshTokenRepository.save(refreshToken);

            tokenReissueResponse = TokenReissue.builder()
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .build();
        }else{
            String accessToken = tokenService.generateAccessToken(currentSocialId, "USER");
            tokenReissueResponse = TokenReissue.builder()
                    .accessToken(token.getAccessToken())
                    .refreshToken(tokenReissueRequest.getRefreshToken())
                    .build();
        }
        return tokenReissueResponse;
    }
}
