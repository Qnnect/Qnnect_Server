package com.qnnect.user.service;

import com.qnnect.cafe.dto.CafeScrapResponse;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.MainResponse;
import com.qnnect.user.dtos.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public void enableNotification(User user, boolean enabledNotification);
    public ProfileResponse updateUserProfile(User user, String nickName, MultipartFile profileImage);
    public MainResponse getMain(User user);
    public List<CafeScrapResponse> getCafeList(User user);
}