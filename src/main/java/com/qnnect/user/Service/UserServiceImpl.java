package com.qnnect.user.Service;

import com.qnnect.common.S3Uploader;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileRequest;
import com.qnnect.user.dtos.ProfileResponse;
import com.qnnect.user.dtos.ProfileUpdateRequest;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    @Override
    public void enableNotification(User user, boolean enabledNotification) {
        if(user.isPushEnabled() != enabledNotification){
            user.setPushEnabled(enabledNotification);
        }
        userRepository.save(user);
    }

    public ProfileResponse updateUserProfile(User user, String nickName, MultipartFile profileImage){
        if(!StringUtils.isEmpty(nickName)){
            user.setNickName(nickName);
        }

        if(profileImage != null){
            try {
                String imageUrl = s3Uploader.upload(profileImage);
                user.setProfilePicture(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
        return ProfileResponse.from(user);
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

