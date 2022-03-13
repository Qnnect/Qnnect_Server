package com.qnnect.ingredients.repository;

import com.qnnect.ingredients.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
