//package linda.myreporter;
//
//import android.app.ActionBar;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.preference.Preference;
//import android.preference.PreferenceActivity;
//import android.view.Menu;
//import android.view.MenuItem;
//
//
//public class SettingsActivity extends PreferenceActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//        addPreferencesFromResource(R.xml.activity_settings);
//        findPreference("clear_db").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
//                builder.setTitle("Confirm Delete Database");
//                builder.setMessage("Are you sure you want to clear all your previously answered questions?");
//                builder.setPositiveButton("YES, delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        QuestionsDatabaseHelper helper = new QuestionsDatabaseHelper(SettingsActivity.this);
//                        SQLiteDatabase db = helper.getWritableDatabase();
//                        helper.onUpgrade(db, 1, 1);
//                    }
//                });
//                builder.setNegativeButton("NO, don't delete", null);
//                builder.create().show();
//                return true;
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_settings, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            return true;
//        } else {
////            return super.onOptionsItemSelected(item);
//            return false;
//        }
//    }
//}
