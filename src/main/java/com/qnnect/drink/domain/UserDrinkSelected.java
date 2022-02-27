package com.qnnect.drink.domain;

import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class UserDrinkSelected {

    @Id
    @Column(name = "USER_DRINK_SELECTED_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DRINK_ID")
    private Drink drink;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "userDrinkSelected")
    private List<DrinkIngredientsFilled> drinkIngredientsFilled = new ArrayList<>();


}
