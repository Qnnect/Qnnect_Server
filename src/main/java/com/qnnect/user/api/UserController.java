package com.qnnect.user.api;

import com.qnnect.cafe.dto.CafeScrapResponse;
import com.qnnect.common.CurrentUser;
import com.qnnect.user.dtos.MainResponse;
import com.qnnect.user.service.UserService;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 API 정보를 제공"})
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "프로필 설정")
    @PatchMapping(path = "/user/profile")
    public ResponseEntity<ProfileResponse> updateProfile(@CurrentUser User user, @RequestPart(required = false) MultipartFile profilePicture,
                                                         @RequestPart(required = false) String nickName) {
        ProfileResponse profileResponse = userService.updateUserProfile(user, nickName, profilePicture);
        return ResponseEntity.ok(profileResponse);
    }

    @ApiOperation(value = "사용자 정보")
    @GetMapping("/user")
    public ResponseEntity<ProfileResponse> getUser(@CurrentUser User user) {
        return ResponseEntity.ok(ProfileResponse.from(user));
    }

    @ApiOperation(value = "알림 설정")
    @PatchMapping("/user/enablenotification")
    public ResponseEntity<Void> enableNotification(@CurrentUser User user, boolean enableNotification) {
        userService.enableNotification(user, enableNotification);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "메인 화면")
    @PatchMapping("/home")
    public ResponseEntity<MainResponse> userMain (@ApiIgnore @CurrentUser User user) {
        MainResponse mainResponse = userService.getMain(user);
        return ResponseEntity.ok(mainResponse);
    }

    @ApiOperation(value = "사용자 카페 리스트")
    @GetMapping("/scrap/cafes")
    public ResponseEntity<List<CafeScrapResponse>> getUserCafeList (@ApiIgnore @CurrentUser User user) {
        List<CafeScrapResponse> cafeList = userService.getCafeList(user);
        return ResponseEntity.ok(cafeList);
    }
}
