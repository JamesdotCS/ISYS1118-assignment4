package tests;

import src.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Test1to4 {
    //before test setup to ensure everything can be run in this one class
    private Course course;

    @BeforeEach 
    void setUp() {
        course = new Course();
    }

    //helper-initilising an assesment that i can change my values of
    private boolean create(String name, String description, int points) {
        return course.createNewAssessment(name, description, points, 
            "online", false, "", "2026-03-01 09:00", 
            "2026-03-15 17:00", "2026-04-01 09:00");
    }

    //Make sure if valid it pasts
    @Test
    void validAssessmentIsAccepted() {
        assertTrue(create("assignment 1", "write a report", 10));
        assertEquals(1, course.getAssessmentsCount());
    }

    //condition one - unique name
    //test that a duplicate is rejected
    @Test 
    void duplicateNameIsRejected() {
        //create an assessment called assignment one
        assertTrue(create("assignment 1", "write a report", 10));
        //create an assessment called assignment one - should fail
        assertFalse(create("assignment 1", "write a shorter report", 20));
        //make sure there is only one assessment that exists
        assertEquals(1, course.getAssessmentsCount());
    }

    //test that non-dulplicate names are accepted
    @Test 
    void differentNameIsAcceoted() {
        //create an assessment called assignment one
        assertTrue(create("assignment 1", "write a report", 10));
        //create an assessment called assignment two - new name so should work
        assertTrue(create("assignment 2", "write a long report", 20));
        //make sure that both assignmkents are counted
        assertEquals(2, course.getAssessmentsCount());
    }

    //condition two - empty names not accepted
    //testing with the following blanks - "", " ", "   ", "\t", and null
    @Test
    void blankNameOneIsRejected() {
        //test with blank name
        assertFalse(create("", "write a report", 10));
        //make sure there is no assignment in the list
        assertEquals(0, course.getAssessmentsCount());
    }
    @Test
    void blankNameTwoIsRejected() {
        //test with blank name
        assertFalse(create(" ", "write a report", 10));
        //make sure there is no assignment in the list
        assertEquals(0, course.getAssessmentsCount());
    }
    @Test
    void blankNameThreeIsRejected() {
        //test with blank name
        assertFalse(create("   ", "write a report", 10));
        //make sure there is no assignment in the list
        assertEquals(0, course.getAssessmentsCount());
    }
    @Test
    void blankNameFourIsRejected() {
        //test with blank name
        assertFalse(create("\t", "write a report", 10));
        //make sure there is no assignment in the list
        assertEquals(0, course.getAssessmentsCount());
    }
    @Test
    void nullNameIsRejected() {
        //test with null
        assertFalse(create(null, "write a report", 10));
        //make sure there is no assignment in the list
        assertEquals(0, course.getAssessmentsCount());
    }

    //condition three
    //test if exactly three words works
    @Test 
    void descriptionWithThreeWords() {
        //test with a three word description
        assertTrue(create("assignment 1", "write a report", 10));
        //make sure there is only one assessment that exists
        assertEquals(1, course.getAssessmentsCount());
    }
    //test if a bunch of words works
    @Test 
    void descriptionWith10Words() {
        //test with a 10 word description
        assertTrue(create("assignment 1", "this is the assignment description for the first assignment students", 10));
        //make sure there is only one assessment that exists
        assertEquals(1, course.getAssessmentsCount());
    }
    //test if two words work
    @Test 
    void descriptionWithTwoWords() {
        //test with a Two word description
        assertFalse(create("assignment 1", "write report", 10));
        //make sure no assessments exist
        assertEquals(0, course.getAssessmentsCount());
    }
    //test if one word works
    @Test 
    void descriptionWithOneWord() {
        //test with a one word description
        assertFalse(create("assignment 1", "report", 10));
        //make sure no assessments exist
        assertEquals(0, course.getAssessmentsCount());
    }
    //test if no words work
    @Test 
    void descriptionWithNoWords() {
        //test with an empty description
        assertFalse(create("assignment 1", "", 10));
        //make sure no assessments exist
        assertEquals(0, course.getAssessmentsCount());
    }
    //test if null description works
    @Test
    void nullDescriptionIsRejected() {
        //test with a null description
        assertFalse(create("assignment 1", null, 10));
        //make sure no assessments exist
        assertEquals(0, course.getAssessmentsCount());
        System.out.println("Created: " + course.getAssessmentsCount());
    }
    
    //condition four 
    //test if the points work
    //more than 0 points
    @Test
    void pointsAboveZero() {
        //test with 10 points
        assertTrue(create("assignment 1", "write a report", 10));
        //make sure an assessment exists
        assertEquals(1, course.getAssessmentsCount());
    }
    //exactly 0 points
    @Test
    void pointsAtZero() {
        //test with 0 points
        assertFalse(create("assignment 1", "write a report", 0));
        //make sure no assessment exists
        assertEquals(0, course.getAssessmentsCount());
    }
    //negative points
    @Test
    void pointsBelowZero() {
        //test with -10 points
        assertFalse(create("assignment 1", "write a report", -10));
        //make sure no assessment exists
        assertEquals(0, course.getAssessmentsCount());
    }
}
