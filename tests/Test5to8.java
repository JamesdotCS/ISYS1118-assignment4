package tests;

import src.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

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

    //Condition 5 - No single assessment can exceed 50 points
    @Test
    void pointsBelowMaximumAccepted() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(create("assessment 1", 49));
        assertEquals(1, course.getAssessmentsCount());
        assertEquals(49, course.getAssessments().get(0).getPoints());
    }

    @Test
    void pointsAtMaximumAccepted() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(create("assessment 1", 50));
        assertEquals(1, course.getAssessmentsCount());
        assertEquals(50, course.getAssessments().get(0).getPoints());
    }

    @Test
    void pointsAboveMaximumRejected() {
        assertEquals(0, course.getAssessmentsCount());

        assertFalse(create("assessment 1", 51));
        assertEquals(0, course.getAssessmentsCount());
    }


}
