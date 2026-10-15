package src;
import java.util.ArrayList;
import java.util.List;

import src.Assessment;

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
                                        String hideDateTime) {
    //Making sure create new assessment fails if one of the conditions are wrong
    //boolean creation - to make final validation (condition 12) easier, making each condition have its own bool
    boolean nameIsUnique = true,
            nameIsNotBlank = true,
            descLeastThreeWords = true,
            pointsPositive = true,
            pointsLessFifty = true,
            totalPointsLess101 = true,
            submissionTypeValid = true,
            hasGroupName = true,
            groupNameUnique = true,
            dateFormatValid = true,
            dateOrderValid = true;

    boolean allInputsValid = true;
    // I have added all condition bools : Jacob
    // They are descriptive for good practice and so they can be used for cross-checking                                           

    //condition 2 - doing first because i can check for null
    if (name == null || name.trim().isEmpty()) {
        nameIsNotBlank = false;
    }
    //condition 1 - check name is unique
    //iterates through all already existing assesments
    //only runs if condition 2 is true, dont want a null pointer exception
    if(nameIsNotBlank) {
        for (Assessment a : assessments) {
            if (a.getName().equals(name)) {
                nameIsUnique = false;
            }
        }
    }
    //condition 3 - description is not null and at least 3 words
    if (description == null || description.trim().isEmpty() ||
        description.trim().split("\\s+").length < 3) {
            descLeastThreeWords = false;
        }    
    //condition 4 - assignment points are more than 0
    if (points <= 0) {
        pointsPositive = false;
    }              
    
    //CONDITION 9 - Grouping Name Must Be Unique for Each Assessment

    if (group && hasGroupName) {
        for (Assessment a : assessments) {
            if (a.getGroupingName().equalsIgnoreCase(groupingName)) {
                groupNameUnique = false;
            }

        }
    }

    //CONDITION 10 - Date time must be formatted correctly
    // Format rules: DD/MM/YYYY hh:mm
    // Must be validated to ensure correct combination, INCLUDING leap years.

    



    //CONDITION 11 - Hide Date Time after Due date time, after publish date time

    if (dateFormatValid) {
        // TODO: Date Order
    }
    

    // Coniditon 12 (check all appropriate validated conditions)

    if ( nameIsUnique && nameIsNotBlank &&
         descLeastThreeWords &&
         pointsPositive && pointsLessFifty && totalPointsLess101 &&
         submissionTypeValid &&
         (!group || (hasGroupName && groupNameUnique)) &&
         dateFormatValid && dateOrderValid ) {
            allInputValid = true;
            system.out.println("TEST: All input valid");
         }

    if (allInputValid){
        assessments.add(new Assessment(name, description, points, submissionType,
                group, groupingName, publishDateTime, dueDateTime, hideDateTime));
    }
    //returns true or false


    return allInputValid;
    }
}

// TODO: Final Check
//if the assessment parameters meet the given conditions,
    // the function should create a new Assessment object,
    // add it in the assessments list and then return true.
    //else
    // the function should only return false.
