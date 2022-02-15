package com.qnnect.diary.domain;

import com.qnnect.drink.domain.DrinkIngredientsFilled;
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
    @Column(name = "ingredients_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name = "user_drink_selected_id")
    private UserDrinkSelected userDrinkSelected;
}
