package com.qnnect.user.api;

import com.qnnect.cafe.dto.CafeScrapResponse;
import com.qnnect.common.CurrentUser;
import com.qnnect.questions.dto.QuestionResponse;
import com.qnnect.user.dtos.MainResponse;
import com.qnnect.user.service.UserService;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<ProfileResponse> updateProfile(@ApiIgnore @CurrentUser User user, @RequestPart(required = false) MultipartFile profilePicture,
                                                         @RequestPart(required = false) String nickName) {
        ProfileResponse profileResponse = userService.updateUserProfile(user, nickName, profilePicture);
        return ResponseEntity.ok(profileResponse);
    }

    @ApiOperation(value = "사진 초기화")
    @PatchMapping(path = "/user/profile/default_image")
    public ResponseEntity<Void> updateProfilePicture(@ApiIgnore @CurrentUser User user) {
        userService.updateToDefaultImage(user);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "사용자 정보")
    @GetMapping("/user")
    public ResponseEntity<ProfileResponse> getUser(@ApiIgnore @CurrentUser User user) {
        return ResponseEntity.ok(ProfileResponse.from(user));
    }

    @ApiOperation(value = "알림 설정")
    @PatchMapping("/user/enablenotification")
    public ResponseEntity<Void> enableNotification(@CurrentUser User user, boolean enableNotification) {
        userService.enableNotification(user, enableNotification);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "메인 화면")
    @GetMapping("/home")
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

    @ApiOperation(value = "사용자 질문 리스트")
    @GetMapping("/user/question/all")
    public ResponseEntity<List<QuestionResponse>> getAllUserQuestionList (@PageableDefault(sort="id", direction = Sort.Direction.DESC)final Pageable pageable, @ApiIgnore @CurrentUser User user) {
        List<QuestionResponse> questionResponse = userService.getQuestionAllList(user, pageable);
        return ResponseEntity.ok(questionResponse);
    }

    @ApiOperation(value = "사용자 신고하기")
    @PostMapping("/user/report")
    public ResponseEntity<Void> reportUser (String userId, @ApiIgnore @CurrentUser User user) {
        userService.reportUser(userId, user);
        return ResponseEntity.ok().build();
    }

//    @ApiOperation(value = "사용자 질문 리스트")
//    @GetMapping("/user/question/")
//    public ResponseEntity<List<CafeScrapResponse>> getAllUserQuestionList (@ApiIgnore @CurrentUser User user) {
//        List<CafeScrapResponse> cafeList = userService.getCafeList(user);
//        return ResponseEntity.ok(cafeList);
//    }
}
