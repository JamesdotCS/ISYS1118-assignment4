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

    //helper for testing different submission types
    private boolean createWithType(String name, String submissionType) {
        return course.createNewAssessment(name,"write a report", 10, submissionType, false, "", "01/03/2026 09:00", "15/03/2026 17:00", "01/04/2026 09:00");
    }

    private boolean createWithGroup(boolean group, String groupingName) {
    return course.createNewAssessment(
        "assessment 1", "write a report", 10, "pdf", group, groupingName, "01/03/2026 09:00", "15/03/2026 17:00", "01/04/2026 09:00");
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

    //Condition 6 - Total assessment points cannot exceed 100
    @Test
    void totalPointsBelowMaximumAccepted() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(create("assessment 1", 50));
        assertTrue(create("assessment 2", 49));

        assertEquals(2, course.getAssessmentsCount());
        assertEquals(49, course.getAssessments().get(1).getPoints());
    }

    @Test
    void totalPointsAtMaximumAccepted() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(create("assessment 1", 50));
        assertTrue(create("assessment 2", 50));

        assertEquals(2, course.getAssessmentsCount());
        assertEquals(50, course.getAssessments().get(1).getPoints());
    }

    @Test
    void totalPointsAboveMaximumRejected() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(create("assessment 1", 50));
        assertTrue(create("assessment 2", 50));

        assertFalse(create("assessment 3", 1));

        assertEquals(2, course.getAssessmentsCount());
        assertEquals(100,
            course.getAssessments().get(0).getPoints()
            + course.getAssessments().get(1).getPoints());
    }

    //Condition 7 - Submission type must be pdf, zip or doc
    @Test
    void pdfSubmissionTypeAccepted() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(createWithType("assessment 1", "pdf"));

        assertEquals(1, course.getAssessmentsCount());
        assertEquals("pdf",
            course.getAssessments().get(0).getSubmissionType());
    }

    @Test
    void zipSubmissionTypeAccepted() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(createWithType("assessment 1", "zip"));

        assertEquals(1, course.getAssessmentsCount());
        assertEquals("zip",
            course.getAssessments().get(0).getSubmissionType());
    }

    @Test
    void docSubmissionTypeAccepted() {
        assertEquals(0, course.getAssessmentsCount());

        assertTrue(createWithType("assessment 1", "doc"));

        assertEquals(1, course.getAssessmentsCount());
        assertEquals("doc",
            course.getAssessments().get(0).getSubmissionType());
    }

    @Test
    void invalidSubmissionTypeRejected() {
        assertEquals(0, course.getAssessmentsCount());

        assertFalse(createWithType("assessment 1", "txt"));

        assertEquals(0, course.getAssessmentsCount());
    }

    @Test
    void nullSubmissionTypeRejected() {
        assertEquals(0, course.getAssessmentsCount());

        assertFalse(createWithType("assessment 1", null));

        assertEquals(0, course.getAssessmentsCount());
    }

    //Condition 8 - Group assessments must have a grouping name
    @Test
    void validGroupNameAccepted() {
        assertTrue(createWithGroup(true, "Group A"));
        assertEquals(1, course.getAssessmentsCount());
    }

    @Test
    void emptyGroupNameRejected() {
        assertFalse(createWithGroup(true, ""));
        assertEquals(0, course.getAssessmentsCount());
    }

    @Test
    void whitespaceGroupNameRejected() {
        assertFalse(createWithGroup(true, "   "));
        assertEquals(0, course.getAssessmentsCount());
    }

    @Test
    void nullGroupNameRejected() {
        assertFalse(createWithGroup(true, null));
        assertEquals(0, course.getAssessmentsCount());
    }

    @Test
    void individualAssessmentWithoutGroupNameAccepted() {
        assertTrue(createWithGroup(false, ""));
        assertEquals(1, course.getAssessmentsCount());
    }

}
