package com.qnnect.drink.apis;

import com.qnnect.drink.dtos.DrinkResponse;
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
@Api(tags = {"음료 관련 API"})
public class DrinkController {

    @ApiOperation(value = "음료 보기 api")
    @GetMapping("/drinks")
    public ResponseEntity<List<DrinkResponse>> getDrinkList(){
        List <DrinkResponse> drinkResponses = new ArrayList<>();
        return ResponseEntity.ok(drinkResponses);
    }

    @ApiOperation(value = "음료에 재료 추가 api")
    @PatchMapping("/drinks/{userDrinkSelectedId}/ingredients/{ingredientsId}")
    public ResponseEntity<Void> addIngredientDrink(@PathVariable Long userDrinkSelectedId,
                                                   @PathVariable Long ingredientsId){
        return ResponseEntity.ok().build();
    }

}
