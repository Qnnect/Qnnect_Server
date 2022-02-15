package com.qnnect.questions.domain;

import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class QuestionUserMade {

    @Id
    @Column(name = "question_user_made_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="question_id")
    private Questions questions;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
