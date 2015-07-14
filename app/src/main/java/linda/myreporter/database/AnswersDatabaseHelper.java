package linda.myreporter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AnswersDatabaseHelper extends SQLiteOpenHelper {
    public AnswersDatabaseHelper(Context context) {
        super (context, AnswersDatabase.DB_NAME, null, AnswersDatabase.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
                AnswersDatabase.TABLE, AnswersDatabase.Columns.QUESTION, AnswersDatabase.Columns.ANSWERS);
        Log.d("AnswersDatabaseHelper", "Query to create table: " + sqlQuery);
        db.execSQL(sqlQuery);

        // TODO prepopulate database with questions and their associated answers
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AnswersDatabase.TABLE);
        Log.d("QuestionsDatabaseHelper", "deleting old database");
        onCreate(db);
        Log.d("QuestionsDatabaseHelper", "creating new database");
    }
}
