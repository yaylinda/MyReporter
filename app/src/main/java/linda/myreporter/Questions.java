package linda.myreporter;

public enum Questions {
    FEELING("Rate how you're currently feeling?", "1 (terrible),2,3,4,5 (wonderful)", false),
    LOCATION("Where are you?", "Home,School,Work,Restaurant,Store,(Other)", true),
    EXCITED("Are you looking forward to anything in the coming week?", "Yes,No", false),
    STRESSED("Are you stressed about anything in the coming week?", "Yes,No", false),
    CAFFEINE("Have you had caffeine today?", "Yes,No", false),
    ALCOHOL("Have you had alcohol today?", "Yes,No", false),
    MARIJUANA("Have you had marijuana today?", "Yes,No", false),
    PEOPLE("Who are you with?", "Myself,Boyfriend,Family,Friends,(Other)", true),
    RELATIONSHIP("Do you currently feel happy about your relationship?", "Yes,No", false),
    CLOTHES("What are you wearing?", "Dress,Bath robe,Nothing,(Other)", true),
    SLEEP("How many hours did you sleep last night?", "4 or less,5,6,7,8,9,10 or more", false),
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
