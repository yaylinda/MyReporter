package linda.myreporter.activity;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import linda.myreporter.Questions;
import linda.myreporter.R;


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
            switch (fragmentNumber) {
                case OVERVIEW:
                    rootView = inflater.inflate(R.layout.overview_fragment_stats, container, false);
                    message = "You have answered a total of " + MainActivity.numEntries + " questions. Select a question from the drawer on the left to see more insight and statistics about it.";
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case FEELING:
                    rootView = inflater.inflate(R.layout.feeling_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case LOCATION:
                    rootView = inflater.inflate(R.layout.location_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case EXCITEMENT:
                    rootView = inflater.inflate(R.layout.excitement_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case DREAD:
                    rootView = inflater.inflate(R.layout.dread_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case FOOD:
                    rootView = inflater.inflate(R.layout.food_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case DRUGS:
                    rootView = inflater.inflate(R.layout.drugs_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case PEOPLE:
                    rootView = inflater.inflate(R.layout.people_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case RELATIONSHIP:
                    rootView = inflater.inflate(R.layout.relationship_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case CLOTHES:
                    rootView = inflater.inflate(R.layout.clothes_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case WISH:
                    rootView = inflater.inflate(R.layout.wish_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case SLEEP:
                    rootView = inflater.inflate(R.layout.sleep_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case EXERCISE:
                    rootView = inflater.inflate(R.layout.exercise_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case LEARN:
                    rootView = inflater.inflate(R.layout.learn_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case PRODUCTIVITY:
                    rootView = inflater.inflate(R.layout.productivity_fragment_stats, container, false);
                    message = "Question: " + Questions.values()[fragmentNumber-2].getQuestionText();
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                case INTERACTIONS:
                    rootView = inflater.inflate(R.layout.interactions_fragment_stats, container, false);
                    message = "Pick two questions to see their correlation: ";
                    ((TextView) rootView.findViewById(R.id.overview_text)).setText(message);
                    return rootView;
                default:
                    // should not get here
                    return null;
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((StatsActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
