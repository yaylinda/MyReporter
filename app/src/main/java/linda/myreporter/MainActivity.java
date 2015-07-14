package linda.myreporter;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;


public class MainActivity extends ListActivity {

    private QuestionsDatabaseHelper helper = new QuestionsDatabaseHelper(MainActivity.this);
    public static long numEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();

        findViewById(R.id.clear_db_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm Delete Database");
                builder.setMessage("Are you sure you want to clear all your previously answered questions?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = helper.getWritableDatabase();
                        helper.onUpgrade(db, 1, 1);
                        updateUI();
                    }
                });
                builder.setNegativeButton("NO", null);
                builder.create().show();
            }
        });
        startService(new Intent(this, NotificationService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_question:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Random Question");
                Random random = new Random();
                final String question = Questions.values()[random.nextInt(Questions.values().length)].getQuestionText();
                builder.setMessage(question);
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String answer = inputField.getText().toString();
                        helper = new QuestionsDatabaseHelper(MainActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.clear();
                        values.put(QuestionsDatabase.Columns.DATE, DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                        values.put(QuestionsDatabase.Columns.QUESTION, question);
                        values.put(QuestionsDatabase.Columns.ANSWER, answer);

                        db.insertWithOnConflict(QuestionsDatabase.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                        updateUI();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;
            case R.id.action_see_stats:
                startActivity(new Intent(this, StatsActivity.class));
            default:
                return false;
        }
    }

    private void updateUI() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(QuestionsDatabase.TABLE,
                new String[]{QuestionsDatabase.Columns._ID, QuestionsDatabase.Columns.DATE, QuestionsDatabase.Columns.QUESTION, QuestionsDatabase.Columns.ANSWER},
                null, null, null, null, QuestionsDatabase.Columns._ID + " DESC");

        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.single_question,
                cursor,
                new String[]{QuestionsDatabase.Columns.DATE, QuestionsDatabase.Columns.QUESTION, QuestionsDatabase.Columns.ANSWER},
                new int[]{R.id.dateText, R.id.questionText, R.id.answerText},
                0
        );
        this.setListAdapter(listAdapter);

        numEntries = DatabaseUtils.queryNumEntries(db, QuestionsDatabase.TABLE);
        findViewById(R.id.clear_db_button).setEnabled(numEntries > 0);

    }
}
