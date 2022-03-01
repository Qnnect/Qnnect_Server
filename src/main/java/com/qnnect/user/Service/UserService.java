package com.qnnect.user.Service;

import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    public void enableNotification(User user, boolean enabledNotification);
    public ProfileResponse updateUserProfile(User user, String nickName, MultipartFile profileImage);
}
