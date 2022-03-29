package com.qnnect.notification.service;


import com.qnnect.notification.domain.Notification;
import com.qnnect.notification.dto.NotificationResponse;
import com.qnnect.notification.repository.NotificationRepository;
import com.qnnect.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getNotification(User user, Pageable pageable){
        List<Notification> notificationList = notificationRepository.findAllByUser_Id(user.getId(), pageable);
        return NotificationResponse.listFrom(notificationList);
    }

    @Override
    public void setNotificationRead(long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setUserChecked(true);
        notificationRepository.save(notification);
    }
}
