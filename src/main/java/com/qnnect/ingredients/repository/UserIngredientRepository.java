package com.qnnect.ingredients.repository;

import com.qnnect.ingredients.domain.Ingredient;
import com.qnnect.ingredients.domain.UserIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserIngredientRepository extends JpaRepository<UserIngredient, Long> {
    @Query("SELECT m,COUNT(i.name) FROM UserIngredient m INNER JOIN m.ingredient i WHERE m.user.id=:userId GROUP BY i.name")
    List<Object[]> CountByIngredientWhereUser_Id(@Param(value="userId") UUID userId);

}
