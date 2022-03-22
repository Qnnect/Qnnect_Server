package com.qnnect.questions.domain;

import com.qnnect.comments.domain.Comment;
import com.qnnect.cafe.domain.Cafe;
import com.qnnect.common.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class CafeQuestion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="CAFE_ID")
    private Cafe cafe;

    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    private Question questions;

    @OneToMany(mappedBy = "cafeQuestion")
    private List<Comment> comments = new ArrayList<>();

}
