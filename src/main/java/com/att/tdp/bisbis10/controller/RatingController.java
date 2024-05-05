package com.att.tdp.bisbis10.controller;


import com.att.tdp.bisbis10.dto.RatingDTO;
import com.att.tdp.bisbis10.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping()
    public ResponseEntity<Void> addRating(@RequestBody @Valid RatingDTO ratingDTO) {
        ratingService.addRating(ratingDTO);
        return ResponseEntity.ok().build();
    }
}
