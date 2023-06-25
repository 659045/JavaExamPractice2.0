package nl.inholland.exam.jason.repository;

import nl.inholland.exam.jason.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    @Query("SELECT COUNT(r) > 0 FROM Review r WHERE r.course.id = :courseId AND r.studentNumber = :studentNumber")
    boolean findByStudentNumberAndCourseId(int studentNumber, Long courseId);
}
