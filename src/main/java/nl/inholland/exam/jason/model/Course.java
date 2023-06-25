package nl.inholland.exam.jason.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Review> reviews;

    @Transient
    private double averageRating;

    public Course(String title, String description, List<Review> reviews) {
        this.title = title;
        this.description = description;
        this.reviews = reviews;
    }
}
