package linda.myreporter;

import java.util.ArrayList;

public enum Questions {
    FEELING("On a scale of 1 (terrible) to 5 (wonderful) rate how you're currently feeling.", "1,2,3,4,5", false),
    LOCATION("Where are you?", "Home,School,Work,Theater,Restaurant,Friend's house,Store,(Other)", true),
    EXCITED("Is there anything that you are looking forward to in the coming week?", "Yes,No", false),
    STRESSED("Is there anything that you are NOT looking forward to in the coming week?", "Yes,No", false),
    FOOD("What have you eaten today?", "Meat,Veggies,Fruit,Grains,Sweets,(Other)", true),
    CHEMICALS("Have you had any caffeine, alcohol, medication, or other drugs today?", "Caffeine,Alcohol,Weed,Medication,(Other)", true),
    PEOPLE("Who are you with?", "Myself,Boyfriend,Family,Friends,(Other)", true),
    RELATIONSHIP("Do you currently feel happy about your relationship?", "Yes,No", false),
    CLOTHES("What are you wearing?", "Dress,Bath robe,Nothing,Normal clothes,(Other)", true),
    WISH("What is something you wish was true about your life right now?", "(Other)", true),
    SLEEP("Good morning! How many hours do you think you slept last night?", "3-,4,5,6,7,8,9,10+", false),
    EXERCISE("Did you exercise today?", "Yes,No", false),
    LEARN("Did you learn anything new today?", "Yes,No", false),
    PRODUCTIVITY("Did you feel that you were productive today?", "Yes,No", false);

    private String question;
    private String possibleAnswers;
    private boolean allowCustomAnswers;

    Questions(String question, String possibleAnswers, boolean allowCustomAnswers) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.allowCustomAnswers = allowCustomAnswers;
    }

    public String getQuestionText() {
        return question;
    }

    public String getPossibleAnswers() {
        return possibleAnswers;
    }

    public void addPossibleAnswers(String newPossibleAnswer) {
        this.possibleAnswers += "," + newPossibleAnswer;
    }

    public boolean isAllowCustomAnswers() {
        return allowCustomAnswers;
    }
}
