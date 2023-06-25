package nl.inholland.exam.jason.controller;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.exam.jason.model.Review;
import nl.inholland.exam.jason.model.dto.ReviewDTO;
import nl.inholland.exam.jason.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews());
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity addReview(@RequestBody ReviewDTO review) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(review));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding the review: " + e.getMessage());
        }
    }

}
