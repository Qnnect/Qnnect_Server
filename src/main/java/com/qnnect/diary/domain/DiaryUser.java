package com.qnnect.diary.domain;

import com.qnnect.drink.domain.UserDrinkSelected;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DiaryUser {

    @Id
    @Column(name = "DIARY_USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;

    @OneToOne
    @JoinColumn(name = "USER_DRINK_SELECTED_ID")
    private UserDrinkSelected userDrinkSelected;
}
