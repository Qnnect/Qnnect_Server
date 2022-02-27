package com.qnnect.user.Service;

import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileRequest;
import com.qnnect.user.dtos.ProfileUpdateRequest;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public void enableNotification(User user, boolean enabledNotification) {
        if(user.isPushEnabled() != enabledNotification){
            user.setPushEnabled(enabledNotification);
        }
        userRepository.save(user);
    }

    public ProfileRequest updateUserProfile(User user, ProfileUpdateRequest profileUpdateRequest){
        if(profileUpdateRequest.getNickName() != null){
            user.setNickName(profileUpdateRequest.getNickName());
        }
        if(profileUpdateRequest.getImage() != null){
            //여기서 파일 s3로 보내는 서비스 호출
        }
        ProfileRequest profileRequest = new ProfileRequest();
        return profileRequest;
    }

//
//    // 현재 SecurityContext 에 있는 유저 정보 가져오기
//    @Transactional(readOnly = true)
//    public UserResponseDto getMyInfo() {
//        return userRepository.findById(SecurityUtil.getCurrentMemberId())
//                .map(UserResponseDto::of)
//                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
//    }

}

