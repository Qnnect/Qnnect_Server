package com.qnnect.comments.domain;

import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String content;

    @Column()
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="CAFE_QUESTION_ID")
    private CafeQuestion cafeQuestion;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replies = new ArrayList<>();
}
