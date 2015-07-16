package linda.myreporter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;

import linda.myreporter.Questions;

public class AnswersDatabaseHelper extends SQLiteOpenHelper {
    public AnswersDatabaseHelper(Context context) {
        super (context, AnswersDatabase.DB_NAME, null, AnswersDatabase.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        String sqlQuery = String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                AnswersDatabase.TABLE, AnswersDatabase.Columns.QUESTION, AnswersDatabase.Columns.ANSWERS);
        db.execSQL(sqlQuery);

        // insert values
        ContentValues values = new ContentValues();
        values.clear();
        for (Questions question : Questions.values()) {
            Log.d("AnswersDatabaseHelper", "inserting: " + question.getQuestionText() + ": " + question.getPossibleAnswers());
            values.put(AnswersDatabase.Columns.QUESTION, question.getQuestionText());
            values.put(AnswersDatabase.Columns.ANSWERS, question.getPossibleAnswers());
            db.insertWithOnConflict(AnswersDatabase.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }


        Log.d("MainActivity", "Number of entries in database: " + DatabaseUtils.queryNumEntries(db, AnswersDatabase.TABLE));
        Log.d("AnswersDatabaseHelper", "creating answers table; pre-populating with answers");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AnswersDatabase.TABLE);
        Log.d("QuestionsDatabaseHelper", "deleting old database");
        onCreate(db);
        Log.d("QuestionsDatabaseHelper", "creating new database");
    }
}
