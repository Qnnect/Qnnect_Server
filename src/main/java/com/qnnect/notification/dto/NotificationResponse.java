package com.qnnect.notification.dto;

import com.qnnect.notification.domain.ENotificationType;
import com.qnnect.notification.domain.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class NotificationResponse {

    private ENotificationType notificationType;

    private String content;

    private String groupName;

    private LocalDate createdAt;

    public static NotificationResponse from(Notification notification){
        String name = "";
        final String questionBase = "질문이 도착했습니다: ";
        final String commentBase = "내 질문에 " + name + "님이 답글을 남겼습니다.";
        final String replyBase = "내 질문에 " + name + "님이 답글을 남겼습니다.";
        String content;

        if(notification.getNotificationType() == ENotificationType.comment){
            name = notification.getSenderName();
            content = commentBase;
        }else if (notification.getNotificationType() == ENotificationType.question){
            content = questionBase;
        }else{
            name = notification.getSenderName();
            content = replyBase;
        }

        name = notification.getSenderName();

        return NotificationResponse.builder().notificationType(notification.getNotificationType())
                .content(content).groupName(notification.getGroupName()).build();
    }
    public static List<NotificationResponse> listFrom(List<Notification> notificationList){
        return notificationList.stream().map(NotificationResponse::from)
                .collect(Collectors.toList());
    }
}
