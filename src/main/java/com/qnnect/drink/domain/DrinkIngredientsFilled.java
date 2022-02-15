package com.qnnect.drink.domain;

import com.qnnect.ingredients.domain.Ingredients;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DrinkIngredientsFilled {

    @Id
    @Column(name = "drink_ingredients_filled_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_drink_selected_id")
    private UserDrinkSelected userDrinkSelected;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredients ingredients;
}
