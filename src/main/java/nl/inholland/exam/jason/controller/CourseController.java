package nl.inholland.exam.jason.controller;

import jakarta.persistence.EntityNotFoundException;
import nl.inholland.exam.jason.model.Course;
import nl.inholland.exam.jason.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseService.getAllCourses());
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity addCourse(@RequestBody Course course) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(course));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable long id, @RequestBody Course course) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(id, course));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
