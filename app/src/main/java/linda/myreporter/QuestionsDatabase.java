package linda.myreporter;

import android.provider.BaseColumns;

public class QuestionsDatabase {
    public static final String DB_NAME = "linda.myreporter.questions";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "questions";

    public class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String DATE = "dateText";
        public static final String QUESTION = "questionText";
        public static final String ANSWER = "answerText";
    }
}
