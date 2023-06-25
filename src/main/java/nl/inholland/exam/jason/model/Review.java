package nl.inholland.exam.jason.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private int rating;
    private int studentNumber;
    private String comment;
    private LocalDate date;

    @ManyToOne
    @JsonIgnoreProperties("reviews")
    @JsonBackReference
    private Course course;

    public Review(int rating, int studentNumber, String comment, LocalDate date, Course course) {
        this.rating = rating;
        this.studentNumber = studentNumber;
        this.comment = comment;
        this.date = date;
        this.course = course;
    }
}