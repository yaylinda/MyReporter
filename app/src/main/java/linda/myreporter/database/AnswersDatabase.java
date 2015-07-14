package linda.myreporter.database;

import android.provider.BaseColumns;

public class AnswersDatabase {
    public static final String DB_NAME = "linda.myreporter.answers";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "answers";

    public class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String QUESTION = "questionText";
        public static final String ANSWERS = "possibleAnswers";
    }
}
