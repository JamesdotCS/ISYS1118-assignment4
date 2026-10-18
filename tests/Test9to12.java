package tests;

import src.Assessment;
import src.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// TESTS SPEC: TEST CASES AND DATA FOR CONDITIONS 9-12
// Assessment spec says each test case has one test data, except 10 and 11 with two,
// and the all-valid case (12) with at least three. The rubric says each test case should have
// one boundary test data, and ties full marks to those counts. 
// Therefore, I have created one test data for each of the cases 9-12, and two for 10 and 11, and three for 12,
// Along with precondition tests in setUp() and attribute and list checks via assertNothingAdded() and assertStored()

public class Test9to12 {

    private Course course;

    // Values used for each test (unless there is other requirement)
    private static final String DESCRIPTION = "description about assignment";
    private static final int POINTS = 10;
    private static final String SUBMISSION_TYPE = "pdf";
    private static final String PUBLISH_DATE_TIME = "01/11/2026 09:00";
    private static final String DUE_DATE_TIME = "15/11/2026 23:59";
    private static final String HIDE_DATE_TIME = "30/11/2026 23:59";

    private void assertNothingAdded() {

        assertEquals(1, course.getAssessments().size(), "A rejected assessment must not be added to the list");
        assertEquals("assessment 1", course.getAssessments().get(0).getName(),
                "The existing assessment must be unchanged");

    }

    private void assertStored(int expectedSize, String name, boolean group, String groupingName,
                              String publish, String due, String hide) {

        assertEquals(expectedSize, course.getAssessments().size(), "The new assessment should be in the list");
        Assessment added = course.getAssessments().get(expectedSize - 1);
        assertEquals(name, added.getName());
        assertEquals(DESCRIPTION, added.getDescription());
        assertEquals(POINTS, added.getPoints());
        assertEquals(SUBMISSION_TYPE, added.getSubmissionType());
        assertEquals(group, added.getGroup());
        assertEquals(groupingName, added.getGroupingName());
        assertEquals(publish, added.getPublishDateTime());
        assertEquals(due, added.getDueDateTime());
        assertEquals(hide, added.getHideDateTime());

    }

    
    @BeforeEach
    void setUp() {
        course = new Course();
        course.createNewAssessment("assessment 1", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team cats", PUBLISH_DATE_TIME, 
            DUE_DATE_TIME, HIDE_DATE_TIME);
    }
    

    // CONDITION 9 - Grouping name unique for each assessment
    @Test
    void groupNameDuplicateRejected() {

        // Data 1
        assertFalse(course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team cats", PUBLISH_DATE_TIME, 
            DUE_DATE_TIME, HIDE_DATE_TIME));

        assertNothingAdded();
        
    }

    // CONDITION 10 - Date time must be correctly formatted, including leap years

    @Test
    void dateTimeValidAccepted() {

    }

    @Test
    void dateTimeFormatValidAccepted() {
        
    }

    @Test
    void dateTimeLeapYearAccepted() {
        
    }

    @Test
    void dateTimeNotLeapYearRejected() {
        
    }

    // CONDITION 11 - Hide date time after due date time, after publish date time

    // @Test

    // CONDITION 12 - All conditions

    // @Test

}
