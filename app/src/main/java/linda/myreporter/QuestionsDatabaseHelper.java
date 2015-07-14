package linda.myreporter;

import android.app.Service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;

public class QuestionsDatabaseHelper extends SQLiteOpenHelper {

    public QuestionsDatabaseHelper(Context context) {
        super (context, QuestionsDatabase.DB_NAME, null, QuestionsDatabase.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
                QuestionsDatabase.TABLE, QuestionsDatabase.Columns.DATE, QuestionsDatabase.Columns.QUESTION, QuestionsDatabase.Columns.ANSWER);
        Log.d("QuestionsDatabaseHelper", "Query to form table: " + sqlQuery);
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsDatabase.TABLE);
        Log.d("QuestionsDatabaseHelper", "deleting old database");
        onCreate(db);
        Log.d("QuestionsDatabaseHelper", "creating new database");
    }
}
