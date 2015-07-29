package linda.myreporter.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.HashMap;
import java.util.Map;

import linda.myreporter.Questions;
import linda.myreporter.R;
import linda.myreporter.database.AnswersDatabase;
import linda.myreporter.database.AnswersDatabaseHelper;
import linda.myreporter.database.QuestionsDatabase;
import linda.myreporter.database.QuestionsDatabaseHelper;


public class StatsActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static final int OVERVIEW = 1;
    private static final int FEELING = 2;
    private static final int LOCATION = 3;
    private static final int EXCITEMENT = 4;
    private static final int DREAD = 5;
    private static final int FOOD = 6;
    private static final int DRUGS = 7;
    private static final int PEOPLE = 8;
    private static final int RELATIONSHIP = 9;
    private static final int CLOTHES = 10;
    private static final int WISH = 11;
    private static final int SLEEP = 12;
    private static final int EXERCISE = 13;
    private static final int LEARN = 14;
    private static final int PRODUCTIVITY = 15;
    private static final int INTERACTIONS = 16;

//    private QuestionsDatabaseHelper questionsHelper = new QuestionsDatabaseHelper(StatsActivity.this);
//    private AnswersDatabaseHelper answersHelper = new AnswersDatabaseHelper(StatsActivity.this);
//    private SQLiteDatabase questionsDB = questionsHelper.getReadableDatabase();
//    private SQLiteDatabase answersDB = answersHelper.getReadableDatabase();

    protected static Map<String, Map<String, Integer>> questionToAnswerToCount = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // extract data for each question
        QuestionsDatabaseHelper questionsHelper = new QuestionsDatabaseHelper(StatsActivity.this);
        SQLiteDatabase questionsDB = questionsHelper.getReadableDatabase();
        AnswersDatabaseHelper answersHelper = new AnswersDatabaseHelper(StatsActivity.this);
        SQLiteDatabase answersDB = answersHelper.getReadableDatabase();
        for (Questions question : Questions.values()) {
            Map<String, Integer> answerToCount = new HashMap<>();
            Cursor questionCursor = questionsDB.rawQuery("SELECT " + QuestionsDatabase.Columns.ANSWER + " FROM " + QuestionsDatabase.TABLE + " WHERE " + QuestionsDatabase.Columns.QUESTION + " == \"" + question.getQuestionText() + "\"", null);
            Cursor answerCursor = answersDB.rawQuery("SELECT " + AnswersDatabase.Columns.ANSWERS + " FROM " + AnswersDatabase.TABLE + " WHERE " + AnswersDatabase.Columns.QUESTION + " == \"" + question.getQuestionText() + "\"", null);
            answerCursor.moveToFirst();
            String[] answersOptions = answerCursor.getString(0).split(",");
            for (String answerOption : answersOptions) {
                answerToCount.put(answerOption, 0);
            }
            if (questionCursor.moveToFirst()) {
                String answer;
                do {
                    answer = questionCursor.getString(0);
                    answerToCount.put(answer, answerToCount.get(answer) + 1);
                } while (questionCursor.moveToNext());

            }
            questionToAnswerToCount.put(question.getQuestionText(), answerToCount);
            Log.d("StatsActivity", "answerToCount for " + question + ": " + answerToCount);
        }
        Log.d("StatsActivity", "questionToAnswerToCount" + questionToAnswerToCount);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
    }

    public void onSectionAttached(int number) {
        if (number == 1) {
            mTitle = getString(R.string.title_section_overview);
        } else if (number-1 <= Questions.values().length) {
            mTitle = Questions.values()[number-2].toString();
        } else {
            mTitle = getString(R.string.title_section_interactions);
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.stats, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_go_home:
                finish();
                return true;
            default:
                return false;

        }
//        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private static int fragmentNumber;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            fragmentNumber = sectionNumber;
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView;
            String message;
            rootView = inflater.inflate(R.layout.overview_fragment_stats, container, false);
            switch (fragmentNumber) {
                case OVERVIEW:
                    container.removeAllViews();
                    message = "You have answered a total of " + MainActivity.numEntries + " questions.\n\nSelect a question from the drawer on the left to see more insight and statistics about it.";
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
//                case FEELING:
//                    rootView = inflater.inflate(R.layout.feeling_fragment_stats, container, false);
//                    question = Questions.values()[fragmentNumber-2].getQuestionText();
//
//                    /*
//                     * making the graph
//                     */
//                    container.addView(makeGraph(question));
//
//                    return rootView;
//                case LOCATION:
//                    rootView = inflater.inflate(R.layout.location_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case EXCITEMENT:
//                    rootView = inflater.inflate(R.layout.excitement_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case DREAD:
//                    rootView = inflater.inflate(R.layout.dread_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case FOOD:
//                    rootView = inflater.inflate(R.layout.food_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case DRUGS:
//                    rootView = inflater.inflate(R.layout.drugs_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case PEOPLE:
//                    rootView = inflater.inflate(R.layout.people_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case RELATIONSHIP:
//                    rootView = inflater.inflate(R.layout.relationship_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case CLOTHES:
//                    rootView = inflater.inflate(R.layout.clothes_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case WISH:
//                    rootView = inflater.inflate(R.layout.wish_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case SLEEP:
//                    rootView = inflater.inflate(R.layout.sleep_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case EXERCISE:
//                    rootView = inflater.inflate(R.layout.exercise_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case LEARN:
//                    rootView = inflater.inflate(R.layout.learn_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
//                case PRODUCTIVITY:
//                    rootView = inflater.inflate(R.layout.productivity_fragment_stats, container, false);
//                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
//                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
//                    return rootView;
                case INTERACTIONS:
                    container.removeAllViews();
                    rootView = inflater.inflate(R.layout.interactions_fragment_stats, container, false);
                    message = "Pick two questions to see their correlation: ";
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                default:
                    String question = Questions.values()[fragmentNumber-2].getQuestionText();
                    container.removeAllViews();
//                    container.addView(makeBarGraph(question));
                    container.addView(makePieGraph(question));
                    return rootView;
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((StatsActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        /**
         *
         * @param question
         * @return
         */
        private GraphicalView makeBarGraph(String question) {
            Map<String, Integer> answerToCount = questionToAnswerToCount.get(question);

            int numAnswers = answerToCount.size();

            Integer[] values = new Integer[numAnswers];
            answerToCount.values().toArray(values);

            String[] labels = new String[numAnswers];
            answerToCount.keySet().toArray(labels);

            int[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.GRAY, Color.CYAN, Color.BLACK, Color.DKGRAY, Color.MAGENTA, Color.WHITE};

            // make series
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            for (int i = 0; i < numAnswers; i++) {
                CategorySeries series = new CategorySeries(labels[i]);
                series.add(labels[i], values[i]);
                dataset.addSeries(i, series.toXYSeries());
            }

            // make renderer
            XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
            renderer.setChartTitle(question);
            renderer.setXTitle("Answer");
            renderer.setYTitle("Count");
            renderer.setYAxisMin(0.0);
            renderer.setYAxisMax(5.0); // TODO make this dynamic
            renderer.setBarWidth(200);
            renderer.setBarSpacing(10);
            renderer.setDisplayValues(true);
            for(int i = 0; i < numAnswers; i++) {
                XYSeriesRenderer xySeriesRenderer = new XYSeriesRenderer();
                xySeriesRenderer.setColor(colors[i]);
                renderer.addSeriesRenderer(i, xySeriesRenderer);
            }

            // return chart object
            return ChartFactory.getBarChartView(getActivity(), dataset, renderer, BarChart.Type.DEFAULT);
        }

        private GraphicalView makePieGraph(String question) {
            Map<String, Integer> answerToCount = questionToAnswerToCount.get(question);

            int numAnswers = answerToCount.size();

            Integer[] values = new Integer[numAnswers];
            answerToCount.values().toArray(values);

            String[] labels = new String[numAnswers];
            answerToCount.keySet().toArray(labels);

            int[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.GRAY, Color.CYAN, Color.BLACK, Color.DKGRAY, Color.MAGENTA, Color.WHITE};

            // Instantiating CategorySeries to plot Pie Chart
            CategorySeries distributionSeries = new CategorySeries(question);
            for (int i = 0; i < numAnswers; i++) {
                // Adding a slice with its values and name to the Pie Chart
                distributionSeries.add(labels[i], values[i]);
            }

            // Instantiating a renderer for the Pie Chart
            DefaultRenderer defaultRenderer = new DefaultRenderer();
            for (int i = 0; i < numAnswers; i++) {
                SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
                seriesRenderer.setColor(colors[i]);
                //Adding colors to the chart
                defaultRenderer.setBackgroundColor(Color.BLACK);
                defaultRenderer.setApplyBackgroundColor(true);
                // Adding a renderer for a slice
                defaultRenderer.addSeriesRenderer(seriesRenderer);
            }

            defaultRenderer.setChartTitle(question);
            defaultRenderer.setChartTitleTextSize(100);
            defaultRenderer.setZoomButtonsVisible(false);


            return ChartFactory.getPieChartView(getActivity(), distributionSeries, defaultRenderer);
        }
    }

}
