package tests;

import src.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test5to8 {

    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course();
    }
    
    //helper method for creating assessments with valid default values
    private boolean create(String name, int points) {
        return course.createNewAssessment(name, "write a report", points, "pdf", false, "", "01/03/2026 09:00", "15/03/2026 17:00", "01/04/2026 09:00");
    }
}
