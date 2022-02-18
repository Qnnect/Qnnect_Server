package com.qnnect.questions.domain;

import com.qnnect.diary.domain.Diary;
import com.qnnect.questions.domain.Questions;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DiaryQuestionWaitingList {
    @Id
    @Column(name = "diary_question_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="diary_id")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Questions questions;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
