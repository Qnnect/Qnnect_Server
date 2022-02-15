package com.qnnect.scrap;

import com.qnnect.questions.domain.EQuestionerType;
import com.qnnect.questions.domain.Questions;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Scraps {
    @Id
    @Column(name = "scrap_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String folderName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions questions;
}