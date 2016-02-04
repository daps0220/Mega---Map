package dpatel96_a3.cs442.com.fooditem;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A fragment representing a single FoodItem detail screen.
 * This fragment is either contained in a {@link FoodItemListActivity}
 * in two-pane mode (on tablets) or a {@link FoodItemDetailActivity}
 * on handsets.
 */
public class FoodItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FoodItemDetailFragment() {
    }

    private FoodItem course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If intent arguments have a course object, get it
        if (getArguments().containsKey("course")) {
            course = getArguments().getParcelable("course");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fooditem_detail, container, false);

        // Display the selected golfcourse, or just a welcome message
        if (course != null) {

            ((TextView) rootView.findViewById(R.id.fooditem_detail)).setText(String.format("%2d. %-20s", course.id, course.name));
            ImageView img = (ImageView)rootView.findViewById(R.id.imageView);
            int id = getResources().getIdentifier("dpatel96_a3.cs442.com.fooditem:mipmap/d"+Integer.toString(course.id),null,null);
            img.setImageResource(id);
            ((TextView) rootView.findViewById(R.id.tv_price)).setText(course.price);
            ((TextView) rootView.findViewById(R.id.tv_description)).setText(course.details);
            ((TextView) rootView.findViewById(R.id.tv_ingredients)).setText(course.ingrediants);
            ((TextView) rootView.findViewById(R.id.tv_recipe)).setText(course.recipe);
            //img.setImageURI(Uri.parse("http://blog.timesunion.com/opinion/files/2012/03/0316_WVfastfood1.jpg"));
        }
        else {
            ((TextView) rootView.findViewById(R.id.fooditem_detail)).setText("Welcome to First Master/Detail");
        }

        return rootView;
    }

}