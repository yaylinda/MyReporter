package linda.myreporter;

import java.util.ArrayList;

public enum Questions {
    FEELING("On a scale of 1 (terrible) to 10 (wonderful) rate how you're currently feeling.", "FEELINGS", new ArrayList<String>()),
    LOCATION("Where are you?", "LOCATION", new ArrayList<String>()),
    EXCITED("Anything you are looking forward to in the coming week?", "EXCITEMENT", new ArrayList<String>()),
    STRESSED("Anything you are NOT looking forward to in the coming week?", "DREAD", new ArrayList<String>()),
    FOOD("What have you eaten today?", "FOOD", new ArrayList<String>()),
    CHEMICALS("Have you had any caffeine, alcohol, medication, or other drugs today?", "DRUGS", new ArrayList<String>()),
    PEOPLE("Who are you with?", "PEOPLE", new ArrayList<String>()),
    RELATIONSHIP("Do you currently feel happy about your relationship?", "RELATIONSHIP", new ArrayList<String>()),
    CLOTHES("What are you wearing?", "CLOTHES", new ArrayList<String>()),
    WISH("What is something you wish was true about your life right now?", "WISH", new ArrayList<String>()),
    SLEEP("Good morning! How many hours do you think you slept last night?", "SLEEP", new ArrayList<String>()),
    EXERCISE("Did you exercise today?", "EXERCISE", new ArrayList<String>()),
    LEARN("Have you learned anything new in the past week?", "LEARN", new ArrayList<String>()),
    PRODUCTIVITY("Did you feel that you were productive today?", "PRODUCTIVITY", new ArrayList<String>());

    // TODO next step: categorize answers, and options for answers

    private String question;
    private String category;
    private ArrayList<String> possibleAnswers;

    Questions(String question, String category, ArrayList<String> possibleAnswers) {
        this.question = question;
        this.category = category;
        this.possibleAnswers = possibleAnswers;
    }

    public String getQuestionText() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void addPossibleAnswers(String newPossibleAnswer) {
        this.possibleAnswers.add(newPossibleAnswer);
    }
}
