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
    @Column(name = "user_drink_selected_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drinks drink;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userDrinkSelected")
    private List<DrinkIngredientsFilled> drinkIngredientsFilled = new ArrayList<>();


}
