package com.qnnect.questions.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private EQuestionerType questionerType;

    @Enumerated(EnumType.STRING)
    private EQuestionType questionType;

    @OneToOne(mappedBy = "questions")
    private QuestionUserMade questionUserMade;
}
