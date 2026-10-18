package tests;

import src.Assessment;
import src.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;


// Tests for conditions 9-12 (12 is the custom one for the assignment, which is all other conditions)
// Jacob Tutungi s4217346

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


    // HELPER METHODS FOR TESTING

    // assertNothingAdded() checks the assessment list is unchanged
    // it checks that it is still of length 1 (for our testing)
    // and that the first assessment is still the same as the one added in setUp()
    private void assertNothingAdded() {

        assertEquals(1, course.getAssessments().size(), "A rejected assessment must not be added to the list");
        assertEquals("assessment 1", course.getAssessments().get(0).getName(),
                "The existing assessment must be unchanged");

    }

    // assertStored() checks that the assessment list has the 
    // expected size, and that the stored assessment is the 
    // same as the one that is passed in as parameters. It checks all attributes.
    private void assertStored(int expectedSize, String name, boolean group, String groupingName,
                              String publish, String due, String hide) {

        // Lots of assertEquals to check each attributes and the size of the list.
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

    
    // Before each test create a course and add it to the assessments list.
    @BeforeEach
    void setUp() {
        course = new Course();
        course.createNewAssessment("assessment 1", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team cats", PUBLISH_DATE_TIME, 
            DUE_DATE_TIME, HIDE_DATE_TIME);

        // ensure that precondition is met by seeing the assessment correctly stored.
        assertStored(1, "assessment 1", true, "team cats", PUBLISH_DATE_TIME, DUE_DATE_TIME, HIDE_DATE_TIME);
    }
    

    // CONDITION/TEST CASE 9 - Grouping name unique for each assessment
    @Test
    void groupNameDuplicateRejected() {

        // Data 1 (non unique group name, should be rejected and return false)
        assertFalse(course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team cats", PUBLISH_DATE_TIME, 
            DUE_DATE_TIME, HIDE_DATE_TIME));

        // not only check that the assessment was rejected but also that existing assessments unchanged
        assertNothingAdded();

    }

    // CONDITION/TEST CASE 10 - Date time must be correctly formatted, including leap years

    @Test
    @DisplayName("31/04/2026 does not exit (April has 30 days) in the publish date, so should be rejected)")
    void dateTimeNonexistentDate() {

        // Data 1 (non existent date, should be rejected and return false)
        boolean result = course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team dogs", "31/04/2026 18:30", 
            DUE_DATE_TIME, HIDE_DATE_TIME);
 
        // not only check that the assessment was rejected but also that existing assessments unchanged
        assertFalse(result);
        assertNothingAdded();
    }

    @Test
    @DisplayName("Hour 24 is out of range in the due date (23:59 is the last valid time) -> false")
    void dateTimeInvalidHour() {

        // Data 2 (invalid hour, should be rejected and return false)
        boolean result = course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team dogs", PUBLISH_DATE_TIME, "15/11/2026 24:00", HIDE_DATE_TIME);

        // ensure nothing added to assessments list
        assertFalse(result);
        assertNothingAdded();
        
    }

    // CONDITION/TEST CASE 11 - Hide date time after due date time, after publish date time
    
    @Test
    @DisplayName("hide time equal to due time (boundary, must be strictly after) -> false")
    void dateTimeHideEqualsDue() {

        // Data 1 (hide time = due time, should be rejected and return false)
        boolean result = course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team dogs", PUBLISH_DATE_TIME, DUE_DATE_TIME, DUE_DATE_TIME);
 
        // ensure nothing added to assessments list
        assertFalse(result);
        assertNothingAdded();
    }

    @Test
    @DisplayName("due time equal to publish time (boundary, must be strictly after) -> false")
    void dateTimeDueEqualsPublish() {

        // Data 2 (due time = publish time, should be rejected and return false)
        boolean result = course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team dogs", PUBLISH_DATE_TIME, PUBLISH_DATE_TIME, HIDE_DATE_TIME);
 
        // ensure nothing added to assessments list
        assertFalse(result);
        assertNothingAdded();
    }

    // CONDITION/TEST CASE 12 - All conditions

    @Test
    @DisplayName("Normal group assessment with a new group name -> true and stored")
    void normalGroupAssessment() {

        // Data 1 (all valid, normal group assessment, should be accepted and return true)
        boolean result = course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, 
            SUBMISSION_TYPE, true, "team dogs", PUBLISH_DATE_TIME, DUE_DATE_TIME, HIDE_DATE_TIME);
 
        // ensure that the assessment was accepted and stored
        assertTrue(result);
        assertStored(2, "assessment 2", true, "team dogs", 
        PUBLISH_DATE_TIME, DUE_DATE_TIME, HIDE_DATE_TIME);
    }

    @Test
    @DisplayName("Normal individual assessment with no grouping name -> true and stored")
    void normalIndividualAssessment() {

        // Data 2 (all valid, individual assessment with no group name, should be accepted and return true)
        boolean result = course.createNewAssessment("assessment 2", DESCRIPTION, POINTS, SUBMISSION_TYPE,
                false, "",  PUBLISH_DATE_TIME, DUE_DATE_TIME, HIDE_DATE_TIME);
 
        // ensure that the assessment was accepted and stored
        assertTrue(result);
        assertStored(2, "assessment 2", false, "", PUBLISH_DATE_TIME, DUE_DATE_TIME, HIDE_DATE_TIME);
    }

    @Test
    @DisplayName("Boundary dates (leap day 29/02/2028, times exactly 1 minute apart) -> true and stored")
    void boundaryDates() {
        String publish = "29/02/2028 00:00"; // valid because 2028 IS a leap year and the earliest time of day
        String due = "29/02/2028 00:01"; // 1 minute after publish
        String hide = "29/02/2028 00:02"; // 1 minute after due
 
        // Data 3 (all valid (but boundary) dates, should be accepted and return true)
        boolean result = course.createNewAssessment("assessment 3", DESCRIPTION, POINTS, SUBMISSION_TYPE,
                true, "team fish", publish, due, hide);
 
        // ensure that the assessment was accepted and stored
        assertTrue(result);
        assertStored(2, "assessment 3", true, "team fish", publish, due, hide);
    }


}
