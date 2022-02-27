package com.qnnect.questions.domain;

import com.qnnect.comments.domain.Comment;
import com.qnnect.diary.domain.Diary;
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
    @Column(name = "DIARY_QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="DIARY_ID")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    private Question questions;

    @OneToMany(mappedBy = "diaryQuestion")
    private List<Comment> comments = new ArrayList<>();
}
