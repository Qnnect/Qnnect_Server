package com.qnnect.user.service;

import com.qnnect.cafe.domain.CafeUser;
import com.qnnect.cafe.repository.CafeUserRepository;
import com.qnnect.common.S3Uploader;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.MainResponse;
import com.qnnect.user.dtos.ProfileResponse;
import com.qnnect.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;
    private final CafeUserRepository cafeUserRepository;

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
            log.info("updated user nickname");
        }

        if(profileImage != null){
            if(user.getProfilePicture() != null){
                s3Uploader.deleteS3(user.getProfilePicture());
            }
            try {
                String imageUrl = s3Uploader.upload(profileImage);
                user.setProfilePicture(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("updated profile image");
        }
        User currUser = userRepository.save(user);
        System.out.println(currUser.getCreatedAt());
        return ProfileResponse.from(user);
    }

    public MainResponse getMain(User user){
        List<CafeUser> cafeUserList = cafeUserRepository.findAllByUser_Id(user.getId());
        System.out.println(cafeUserList.size());
        if(cafeUserList == null){
            return new MainResponse(user, null);
        }
        return new MainResponse(user, cafeUserList);
    }

}

