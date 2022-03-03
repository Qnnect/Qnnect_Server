package com.qnnect.questions.domain;

import com.qnnect.cafe.domain.Cafe;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CafeQuestionWaitingList {
    @Id
    @Column(name="CAFE_QUESTION_WAITING_LIST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="CAFE_ID")
    private Cafe cafe;

    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    private Question questions;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
}
