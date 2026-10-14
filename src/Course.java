package course;
import java.util.List;

class Assessment {
    private String name;
    private String description;
    private int points;
    private String submissionType;
    private boolean group;
    private String groupingName;
    private String publishDateTime;
    private String dueDateTime;
    private String hideDateTime;
    public Assessment(String name, String description, int points,
                    String submissionType, boolean group, String groupingName,
                    String publishDateTime, String dueDateTime, String
        hideDateTime) {
        this.name = name;
        this.description = description;
        this.points = points;
        this.submissionType = submissionType;
        this.group = group;
        this.groupingName = groupingName;
        this.publishDateTime = publishDateTime;
        this.dueDateTime = dueDateTime;
        this.hideDateTime = hideDateTime;
        //TODO: initilise all the attributes
    }
}

public class Course {
    private List<Assessment> assessments;

    public boolean createNewAssessment(String name, String description, int points,
                                        String submissionType, boolean group, String
                                        groupingName,
                                        String publishDateTime, String dueDateTime,
                                        String hideDateTime) {
                                        boolean valid = true;
    //TODO: Validate all the provided parameters.
    //if the assessment parameters meet the given conditions,
    // the function should create a new Assessment object,
    // add it in the assessments list and then return true.
    //else
    // the function should only return false.
    boolean nameIsUnique = true,
            nameIsNotBlank = true,
            descLeastThreeWords = true,
            assPointsPositive = true,
            assPointsLTFifty = true,
            totalPointsLess101 = true,
            submissionTypeValid = true,
            hasGroupName = true,
            groupNameUnique = true,
            dateFormatValid = true,
            dateOrderValid = true;

    if ( nameIsUnique && nameIsNotBlank &&
         descLeastThreeWords &&
         assPointsPositive && assPointsLTFifty && totalPointsLess101 &&
         submissionTypeValid &&
         (!group || (hasGroupName && groupNameUnique)) &&
         dateFormatValid && dateOrderValid ) {
            allInputValid = true;
            system.out.println("TEST: All input valid");
         }







    )


    return valid;
    }
}

// TEST PUSH - ISYSa4 Jacob James Kashifa
