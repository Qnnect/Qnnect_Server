package com.qnnect.ingredients.repository;

import com.qnnect.ingredients.domain.EIngredientType;
import com.qnnect.ingredients.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByIngredientType(@Param(value = "ingredientType")EIngredientType ingredientType);
}
