package com.qnnect.cafe.domain;

import com.qnnect.common.domain.BaseTimeEntity;
import com.qnnect.questions.domain.CafeQuestion;
import com.qnnect.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany(mappedBy = "cafe",cascade = CascadeType.ALL)
    private List<CafeQuestion> cafeQuestion;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<CafeUser> cafeUsers;

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
