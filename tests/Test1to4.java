package tests;
import src.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestsOneToFour {

    @Test
    //condition One test - new assignment, not matching name with another assignment
    boolean uniqueAssesmentName() {
        // 1. test intialising
        String assesmentName = "AssignmentOne";
        Course testing = new Course();
        boolean validName = true;


        // 2. test
        int length = testing.getAssessmentsCount();

        // 3. ASSERT (Verify the output matches expectations)
        // assertEquals(expected, actual, optional_failure_message)
        assertEquals(30, result, "10 + 20 should equal 30"); 
    }
}
