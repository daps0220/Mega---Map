package dpatel96_a3.cs442.com.fooditem;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import java.util.ArrayList;

public class FoodItemListActivity extends Activity
        implements FoodItemListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private static  String TAG = "FoodItemListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get golf courses from a file named courses.txt
        FoodModel dataModel = new FoodModel(this, "courses");
        ArrayList<FoodItem> courses = dataModel.getCourses();

        FragmentManager fm = getFragmentManager();
        setContentView(R.layout.activity_fooditem_list);

        if (findViewById(R.id.golfcourse_detail_container) != null) {
            mTwoPane = true;
            // Its a tablet, so create a new detail fragment if one does not already exist
            FoodItemDetailFragment df = (FoodItemDetailFragment) fm.findFragmentByTag("Detail");
            if (df == null) {
                // Initialize new detail fragment
                df = new FoodItemDetailFragment();
                Bundle args = new Bundle();
                args.putParcelable("course", new FoodItem("Welcome to First Master/Detail"));
                df.setArguments(args);
                fm.beginTransaction().replace(R.id.golfcourse_detail_container, df, "Detail").commit();
            }
        }

        // Initialize a new golfcourse list fragment, if one does not already exist
        FoodItemListFragment cf = (FoodItemListFragment) fm.findFragmentByTag("List");
        if ( cf == null) {
            cf = new FoodItemListFragment();
            Bundle arguments = new Bundle();
            arguments.putParcelableArrayList("courses", dataModel.getCourses());
            cf.setArguments(arguments);
            fm.beginTransaction().replace(R.id.golfcourse_list, cf, "List").commit();
        }
    }

    /**
     * Callback method from {@link FoodItemListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(FoodItem c) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            // Pass the selected FoodItem object to the DetailFragment
            arguments.putParcelable("course", c);
            FoodItemDetailFragment fragment = new FoodItemDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.golfcourse_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, FoodItemDetailActivity.class);
            // Pass the selected FoodItem object to the DetailActivity
            detailIntent.putExtra("course", c);
            startActivity(detailIntent);
        }
    }
}
