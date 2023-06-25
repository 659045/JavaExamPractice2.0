package nl.inholland.exam.jason.service;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.exam.jason.model.Course;
import nl.inholland.exam.jason.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        try {
            List<Course> courses = (List<Course>) courseRepository.findAll();

            courses.forEach(course -> {
                double averageRating = course.getReviews().stream()
                        .map(review -> review.getRating())
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0.0);
                course.setAverageRating(averageRating);
            });

            return courses;

        } catch (Exception e) {
            throw new EntityNotFoundException("No courses found");
        }
    }

    public Course getCourseById(long id) {
        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    public Course addCourse(Course course) {
        return (Course) courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
            Course courseToUpdate = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));

            courseToUpdate.setTitle(course.getTitle());
            courseToUpdate.setDescription(course.getDescription());
            courseToUpdate.setReviews(course.getReviews());
            return (Course) courseRepository.save(courseToUpdate);
    }
}
