package com.qnnect.notification.apis;

import com.qnnect.common.CurrentUser;
import com.qnnect.notification.dto.NotificationResponse;
import com.qnnect.notification.service.NotificationService;
import com.qnnect.questions.dto.QuestionDetailResponse;
import com.qnnect.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"알림 관련 API"})
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notification")
    @ApiOperation(value = "알림 가져오기 api")
    public ResponseEntity<List<NotificationResponse>> getNotificationList(@ApiIgnore @CurrentUser User user, @PageableDefault(sort="id", direction = Sort.Direction.DESC)final Pageable pageable){
        List<NotificationResponse> notificationResponse = notificationService.getNotification(user, pageable);
        return ResponseEntity.ok(notificationResponse);
    }
}
