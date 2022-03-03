package com.qnnect.cafe.domain;

import com.qnnect.common.domain.BaseTimeEntity;
import com.qnnect.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Cafe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private EGroupType groupType;

    @Enumerated(EnumType.STRING)
    private EQuestionCycle questionCycle;

    @Enumerated(EnumType.STRING)
    private EDiaryColor diaryColor;

    private String code;

    @ManyToOne()
    @JoinColumn(name="USER_ID")
    private User organizer;

    @Builder
    public Cafe(String title, EGroupType groupType, EQuestionCycle questionCycle,
                EDiaryColor diaryColor, String code, User organizer){
        this.title = title;
        this.groupType = groupType;
        this.questionCycle = questionCycle;
        this.diaryColor = diaryColor;
        this.code = code;
        setOrganizer(organizer);
    }

    public void setOrganizer(User organizer){
        if (Objects.isNull(this.organizer)) {
            this.organizer = organizer;
        }
    }
}
