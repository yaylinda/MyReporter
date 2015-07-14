package linda.myreporter.activity;

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
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

import linda.myreporter.Questions;
import linda.myreporter.R;
import linda.myreporter.database.AnswersDatabaseHelper;
import linda.myreporter.database.QuestionsDatabase;
import linda.myreporter.database.QuestionsDatabaseHelper;
import linda.myreporter.service.NotificationService;


public class MainActivity extends ListActivity {

    private QuestionsDatabaseHelper questionsHelper = new QuestionsDatabaseHelper(MainActivity.this);
    private AnswersDatabaseHelper answersHelper = new AnswersDatabaseHelper(MainActivity.this);
    public static long numEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();

        // todo: move to settings
        findViewById(R.id.clear_db_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm Delete Database");
                builder.setMessage("Are you sure you want to clear all your previously answered questions?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = questionsHelper.getWritableDatabase();
                        questionsHelper.onUpgrade(db, 1, 1);
                        MainActivity.this.updateUI();
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
//                final EditText inputField = new EditText(this);
                final RadioButton option1 = new RadioButton(this);
                option1.setText("button");
                builder.setView(option1);
                // TODO more buttons
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String answer = option1.getText().toString();
                        questionsHelper = new QuestionsDatabaseHelper(MainActivity.this);
                        SQLiteDatabase db = questionsHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();

                        values.clear();
                        values.put(QuestionsDatabase.Columns.DATE, DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
                        values.put(QuestionsDatabase.Columns.QUESTION, question);
                        values.put(QuestionsDatabase.Columns.ANSWER, answer);

                        db.insertWithOnConflict(QuestionsDatabase.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                        MainActivity.this.updateUI();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;
            case R.id.action_see_stats:
                startActivity(new Intent(this, StatsActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return false;
        }
    }

    private void updateUI() {
        SQLiteDatabase db = questionsHelper.getReadableDatabase();
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
