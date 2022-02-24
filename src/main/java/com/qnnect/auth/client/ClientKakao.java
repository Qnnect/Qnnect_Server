package com.qnnect.auth.client;

import com.qnnect.auth.dto.KakaoResponse;
import com.qnnect.auth.token.TokenValidFailedException;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientKakao implements ClientProxy {

    @Autowired
    private final WebClient webClient;

    @Override
    public User getUserData(String accessToken) {
        KakaoResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoResponse.class)
                .block();

        System.out.println(kakaoUserResponse.getId());

        return User.builder()
                .socialId(String.valueOf(kakaoUserResponse.getId()))
                .profilePicture(kakaoUserResponse.getKakaoAccount().
                        getProfile().getProfileImageUrl() != null ? kakaoUserResponse.getKakaoAccount().
                        getProfile().getProfileImageUrl() : "")
                .build();
    }
}