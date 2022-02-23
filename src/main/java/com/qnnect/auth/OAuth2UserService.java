package com.qnnect.auth;

import com.qnnect.auth.client.ClientKakao;
import com.qnnect.auth.dto.AuthRequest;
import com.qnnect.auth.token.*;
import com.qnnect.auth.dto.AuthResponse;
import com.qnnect.user.domain.User;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final ClientKakao clientKakao;
    private final RefreshTokenRepository refreshTokenRepository;
    private RefreshToken refreshToken;

    public AuthResponse signUpOrLogIn(AuthRequest authRequest) {
        System.out.println("signing up ");
        User user;
        if (authRequest.getLoginType() == ELoginType.kakao) {
            user = getKakaoProfile(authRequest.getAccessToken());
        } else {
            user = getKakaoProfile(authRequest.getAccessToken());
        }
        Token token = tokenService.generateToken(user.getSocialId(), "USER");
        boolean isNewMember = false;

        if (userRepository.findBySocialId(user.getSocialId()).equals(Optional.empty())) {
            log.debug("saving user");
            userRepository.save(user);
            refreshToken = RefreshToken.builder()
                    .key(user.getSocialId())
                    .value(token.getRefreshToken())
                    .build();
            refreshTokenRepository.save(refreshToken);
            isNewMember = true;
        } else {
            log.debug("user exists");
            Optional<RefreshToken> oldRefreshToken = refreshTokenRepository.findByKey(user.getSocialId());
            if (!oldRefreshToken.equals(Optional.empty())) {
                refreshToken.updateValue(token.getRefreshToken());
            } else {
                refreshToken = RefreshToken.builder()
                        .key(user.getSocialId())
                        .value(token.getRefreshToken())
                        .build();
                refreshTokenRepository.save(refreshToken);
            }

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
}
