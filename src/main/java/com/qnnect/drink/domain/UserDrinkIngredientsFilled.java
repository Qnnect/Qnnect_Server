package com.qnnect.drink.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qnnect.ingredients.domain.Ingredients;
import com.qnnect.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserDrinkIngredientsFilled {

    @Id
    @Column(name = "userdrink_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drinks drink;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredients ingredients;
}
