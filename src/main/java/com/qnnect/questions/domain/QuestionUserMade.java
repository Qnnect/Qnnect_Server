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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="QUESTION_ID")
    private Question questions;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
}
