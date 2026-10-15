package src;
import java.util.ArrayList;
import java.util.List;

public class Course {
    //changed this line so it doesnt give a null pointer exception
    private List<Assessment> assessments = new ArrayList<>();
    //adding getters
    public int getAssessmentsCount() {
        return this.assessments.size();
    }
    public List<Assessment> getAssessments() {
        return this.assessments;
    }
    public boolean createNewAssessment(String name, String description, int points,
                                        String submissionType, boolean group, String
                                        groupingName,
                                        String publishDateTime, String dueDateTime,
                                        String hideDateTime) {boolean valid = true;
    //Making sure create new assessment fails if one of the conditions are wrong
    //boolean creation - to make condition 12 easier, making each condition have its own bool
    boolean condition1 = true;
    boolean condition2 = true;
    boolean condition3 = true;
    boolean condition4 = true;
    // TODO - add your condition bools here                                            

    //condition 2 - doing first because i can check for null
    if (name == null || name.trim().isEmpty()) {
        condition2 = false;
    }
    //condition 1 - check name is unique
    //iterates through all already existing assesments
    //only runs if condition 2 is true, dont want a null pointer exception
    if(condition2) {
        for (Assessment a : assessments) {
            if (a.getName().equals(name)) {
                condition1 = false;
            }
        }
    }
    //condition 3 - description is not null and at least 3 words
    if (description == null || description.trim().isEmpty() ||
        description.trim().split("\\s+").length < 3) {
            condition3 = false;
        }    
    //condition 4 - assignment points are more than 0
    if (points <= 0) {
        condition4 = false;
    }                              
    //TODO: Validate all the provided parameters.
    //if the assessment parameters meet the given conditions,
    // the function should create a new Assessment object,
    // add it in the assessments list and then return true.
    //else
    // the function should only return false.
    
    //TODO add your conditions into this- jacob feel free to change it for 12, making sure my tests can run
    if(!(condition1 && condition2 && condition3 && condition4)) {
        valid = false;
    }
    //if valid, creates new assessment, otherwise not
    if (valid){
        assessments.add(new Assessment(name, description, points, submissionType,
                group, groupingName, publishDateTime, dueDateTime, hideDateTime));
    }
    //returns true or false
    return valid;
    }
}

// TEST PUSH - ISYSa4 Jacob James Kashifa
