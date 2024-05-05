package com.att.tdp.bisbis10.controller;


import com.att.tdp.bisbis10.dto.DishDTO;
import com.att.tdp.bisbis10.dto.DishUpdateDTO;
import com.att.tdp.bisbis10.entitys.Dish;
import com.att.tdp.bisbis10.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantID}/dishes")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addDish(@PathVariable Long restaurantID, @RequestBody @Valid DishDTO dishDTO) {
        dishService.addDish(restaurantID, dishDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{dishID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Dish>> deleteDish(@PathVariable Long restaurantID, @PathVariable Long dishID) {
        dishService.deleteDish(restaurantID, dishID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{dishID}")
    public ResponseEntity<List<Dish>> updateDish(@PathVariable Long restaurantID, @PathVariable Long dishID,
                                                 @RequestBody @Valid DishUpdateDTO dishUpdateDTO) {
        dishService.updateDish(restaurantID, dishID, dishUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<Dish>> getDishesByRestaurantID(@PathVariable Long restaurantID) {

        return ResponseEntity.ok(dishService.getRestaurantDishes(restaurantID));
    }


}
