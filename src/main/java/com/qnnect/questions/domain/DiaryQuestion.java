package com.qnnect.questions.domain;

import com.qnnect.comments.domain.Comments;
import com.qnnect.diary.domain.Diary;
import com.qnnect.questions.domain.Questions;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "diaryQuestion")
    private List<Comments> comments = new ArrayList<>();
}
