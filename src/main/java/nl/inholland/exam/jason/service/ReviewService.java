package nl.inholland.exam.jason.service;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.exam.jason.model.Review;
import nl.inholland.exam.jason.model.dto.ReviewDTO;
import nl.inholland.exam.jason.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CourseService courseService;

    public ReviewService(ReviewRepository reviewRepository, CourseService courseService) {
        this.reviewRepository = reviewRepository;
        this.courseService = courseService;
    }

    public List<Review> getAllReviews() {
        try {
            return (List<Review>) reviewRepository.findAll();
        } catch (Exception e) {
            throw new EntityNotFoundException("No reviews found");
        }
    }

    public Review addReview(ReviewDTO review) {
            Review newReview = new Review();

            if (reviewRepository.findByStudentNumberAndCourseId(review.getStudentNumber(), review.getCourseId())) {
                throw new EntityNotFoundException("You have already reviewed this course");
            }

            newReview.setRating(review.getRating());
            newReview.setStudentNumber(review.getStudentNumber());
            newReview.setComment(review.getComment());
            newReview.setDate(LocalDate.now());
            newReview.setCourse(courseService.getCourseById(review.getCourseId()));

            return (Review) reviewRepository.save(newReview);
    }
}
