package com.qnnect.questions.domain;

import com.qnnect.diary.domain.Diary;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DiaryQuestionWaitingList {
    @Id
    @Column(name="DIARY_QUESTION_WAITING_LIST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="DIARY_ID")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    private Question questions;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
}
