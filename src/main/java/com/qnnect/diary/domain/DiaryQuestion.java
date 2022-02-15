package com.qnnect.diary.domain;

import com.qnnect.questions.domain.Questions;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DiaryQuestion {

    @Id
    @Column(name = "diary_question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="diary_id")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Questions questions;
}
