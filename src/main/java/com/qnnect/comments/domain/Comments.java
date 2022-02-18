package com.qnnect.comments.domain;

import com.qnnect.diary.domain.DiaryQuestion;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="diary_question")
    private DiaryQuestion diaryQuestion;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replies = new ArrayList<>();
}
