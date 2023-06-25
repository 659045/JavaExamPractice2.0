package nl.inholland.exam.jason.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDTO {

    private Long courseId;
    private int rating;
    private int studentNumber;
    private String comment;
}
