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
    //condition 2 - doing first because i can check for null
    if (name == null || name.trim().isEmpty()) {
        //returns false here instead of checking valid because interactions on null later will cause issues
        return false;
    }
    //condition 1 - check name is unique
    //iterates through all already existing assesments
    for (Assessment a : assessments) {
        if (a.getName().equals(name)) {
            valid = false;
        }
    }
    //condition 3 - description is not null and at least 3 words
    if (description == null || description.trim().isEmpty() ||
        description.trim().split("\\s+").length < 3) {
            valid = false;
        }    
    //condition 4 - assignment points are more than 0
    if (points <= 0) {
        valid = false;
    }                              
    //TODO: Validate all the provided parameters.
    //if the assessment parameters meet the given conditions,
    // the function should create a new Assessment object,
    // add it in the assessments list and then return true.
    //else
    // the function should only return false.

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
