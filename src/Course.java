package src; // maybe update package paths
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

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

    // DATE/TIME HELPER METHODS FOR CONDITION 10 AND 11

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
                         .withResolverStyle(ResolverStyle.STRICT); // Strick resolver style ensures that invalid dates (like 30/02/2023) are rejected

    static LocalDateTime parseDateTime(String input) {
        if (input == null) return null;
        try {
            return LocalDateTime.parse(input, FMT);
        } catch (DateTimeParseException e) {
            return null;   // null means invalid
        }
    }

    public static boolean isValidDateTime(String input) {
        return parseDateTime(input) != null;
    }

    static boolean areInOrder(String d1, String d2, String d3) {
        LocalDateTime a = parseDateTime(d1);
        LocalDateTime b = parseDateTime(d2);
        LocalDateTime c = parseDateTime(d3);

        if (a == null || b == null || c == null) return false;  // format check fails first

        return a.isBefore(b) && b.isBefore(c);
    }


    public boolean createNewAssessment(String name, String description, int points,
                                        String submissionType, boolean group, String
                                        groupingName,
                                        String publishDateTime, String dueDateTime,
                                        String hideDateTime) {
    //Making sure create new assessment fails if one of the conditions are wrong
    //boolean creation - to make final validation (condition 12) easier, making each condition have its own bool
    boolean nameUnique = true,
            nameNotBlank = true,
            descLeastThreeWords = true,
            pointsPositive = true,
            pointsLessFifty = true,
            totalPointsLess101 = true,
            submissionTypeValid = true,
            hasGroupName = true,
            groupNameUnique = true,
            dateFormatValid = true,
            dateOrderValid = true;

    boolean allInputValid = false;
    // I have added all condition bools : Jacob
    // They are descriptive for good practice and so they can be used for cross-checking                                           

    //condition 2 - doing first because i can check for null
    if (name == null || name.trim().isEmpty()) {
        nameNotBlank = false;
    }
    //condition 1 - check name is unique
    //iterates through all already existing assesments
    //only runs if condition 2 is true, dont want a null pointer exception
    if(nameNotBlank) {
        for (Assessment a : assessments) {
            if (a.getName().equals(name)) {
                nameUnique = false;
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
    
    //Condition 5 - No single assessment can exceed 50 points
    if (points > 50) {
        pointsLessFifty = false;
    }

    //Condition 6 - Total assessment points cannot exceed 100
    int totalPoints = points;

    for (Assessment a : assessments) {
        totalPoints += a.getPoints();
    }

    if (totalPoints > 100) {
        totalPointsLess101 = false;
    }

    //Condition 7 - Submission type must be pdf, zip or doc
    if (submissionType == null || (!submissionType.equals("pdf") && !submissionType.equals("zip") && !submissionType.equals("doc"))) {
        submissionTypeValid = false;
    }

    //Condition 8 - Group assessments must have a grouping name
    if (group && (groupingName == null || groupingName.trim().isEmpty())) {
        hasGroupName = false;
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
    // Must be validated to ensure correct combination, INCLUDING leap years. Strict.
    // See helper methods above for date time validation and parsing

    if (!isValidDateTime(publishDateTime) || !isValidDateTime(dueDateTime) || !isValidDateTime(hideDateTime)) {
        dateFormatValid = false;
    } 

    //CONDITION 11 - Hide Date Time after Due date time, after publish date time

    if (dateFormatValid) {
        if (!areInOrder(publishDateTime, dueDateTime, hideDateTime)) {
            dateOrderValid = false;
        }
    }    

    // Coniditon 12 (check all appropriate validated conditions)

    if ( nameUnique && nameNotBlank &&
         descLeastThreeWords &&
         pointsPositive && pointsLessFifty && totalPointsLess101 &&
         submissionTypeValid &&
         (!group || (hasGroupName && groupNameUnique)) &&
         dateFormatValid && dateOrderValid ) {
            allInputValid = true;
            // System.out.println("TEST: All input valid, assessment created");
         } else {
            allInputValid = false;
            // System.out.println("TEST: Some input NOT valid, assessment NOT created");
         }

    if (allInputValid){
        assessments.add(new Assessment(name, description, points, submissionType,
                group, groupingName, publishDateTime, dueDateTime, hideDateTime));
    }
    //returns true or false


    return allInputValid;
    }
}