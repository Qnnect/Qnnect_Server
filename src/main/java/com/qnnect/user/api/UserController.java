package com.qnnect.user.api;

import com.qnnect.common.CurrentUser;
import com.qnnect.user.Service.UserService;
import com.qnnect.user.domain.User;
import com.qnnect.user.dtos.ProfileResponse;
import com.qnnect.user.dtos.ProfileUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"사용자 API 정보를 제공"})
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "프로필 설정")
    @PatchMapping(path= "/user/profile")
    public ResponseEntity<ProfileResponse> updateProfile(@CurrentUser User user, @RequestPart(value="profile Pricture")MultipartFile multipartFile,
                                              @RequestPart(value="nick name")String nickName){
        ProfileResponse profileResponse = userService.updateUserProfile(user, nickName, multipartFile);
        return ResponseEntity.ok(profileResponse);
    }

//    @RequestMapping(path = "/files", method = RequestMethod.POST,
//            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public @ResponseBody ResponseEntity<String> add(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("name") String name, @RequestParam("brand") String brand) {
//        System.out.println(file.getOriginalFilename() + "\n" + name + "\n" + brand);
//        return new ResponseEntity<>("Created", HttpStatus.OK);
//    }

    @ApiOperation(value = "사용자 정보")
    @GetMapping("/user")
    public ResponseEntity<ProfileResponse> getUser(@CurrentUser User user){
        return ResponseEntity.ok(ProfileResponse.from(user));
    }

    @ApiOperation(value = "알림 설정")
    @PatchMapping("/user/enablenotification")
    public ResponseEntity<Void> enableNotification (@CurrentUser User user, boolean enableNotification){
        userService.enableNotification(user, enableNotification);
        return ResponseEntity.ok().build();
    }

//    @ApiOperation(value = "알림 설정")
//      @PatchMapping("/user/enablenotification")
//    public ResponseEntity<Void> updateNickname(@RequestParam String nickName){
//        return ResponseEntity.created(URI.create("/api/v1/user/")).build();
//    }


//    private final UserService userService;
//
//    @GetMapping("/me")
//    public ResponseEntity<UserResponseDto> getMyMemberInfo() {
//        return ResponseEntity.ok(userService.getMyInfo());
//    }

//    @GetMapping("/{email}")
//    public ResponseEntity<UserResponseDto> getMemberInfo(@PathVariable String email) {
//        return ResponseEntity.ok(userService.getMemberInfo(email));
//    }

}
