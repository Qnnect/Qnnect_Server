package com.qnnect.notification.domain;

import com.qnnect.common.domain.BaseTimeEntity;
import com.qnnect.notification.domain.ENotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private ENotificationType notificationType;

    private String content;

    private boolean userChecked;
}
