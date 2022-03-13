package com.qnnect.ingredients.repository;

import com.qnnect.ingredients.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIngredientRepository extends JpaRepository<Ingredient, Long> {
}
