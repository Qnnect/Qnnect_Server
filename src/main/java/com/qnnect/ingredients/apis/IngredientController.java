package com.qnnect.ingredients.apis;

import com.qnnect.drink.dtos.DrinkResponse;
import com.qnnect.ingredients.dto.IngredientResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"재료 관련 API"})
public class IngredientController {

//    @ApiOperation(value = "재료 보기 api")
//    @GetMapping("/ingredients")
//    public ResponseEntity<List<IngredientResponse>> getIngredientList(){
//        List <IngredientResponse> ingredientResponses = ;
//        return ResponseEntity.ok(ingredientResponses);
//    }

    @ApiOperation(value = "재료 구매 api")
    @PatchMapping("/ingredients/{ingredientsId}")
    public ResponseEntity<Void> addIngredientDrink(@PathVariable Long ingredientsId){
        return ResponseEntity.ok().build();
    }

}
