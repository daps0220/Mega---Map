package dpatel96_a3.cs442.com.fooditem;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A list fragment representing a list of Golfcourses. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link FoodItemDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class FoodItemListFragment extends ListFragment {

    private static final String TAG = "GolfcourseLIstFragment";

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(FoodItem c);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        // Modify the local dummy callback to also pass FoodItem
        public void onItemSelected(FoodItem c) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FoodItemListFragment() {
    }

    private ArrayList<FoodItem> courses = new ArrayList<FoodItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get courses array from argument
        if (getArguments() != null && getArguments().containsKey("courses")) {
            courses = getArguments().getParcelableArrayList("courses");
        }
        // Initialize the display adapter
      /*  ArrayList<String> items= new ArrayList<String>();
        Iterator i = courses.iterator();
        while(i.hasNext()){
            FoodItem temp =(FoodItem) i.next();
            String strtmp = temp.name+" "+temp.holes;
            items.add(strtmp);
        }
        */
        setListAdapter(new ArrayAdapter<FoodItem>(getActivity(),
                android.R.layout.simple_list_item_activated_2,android.R.id.text1,
                courses){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);


                text1.setText(courses.get(position).id + ". " + courses.get(position).name);
                text2.setText(courses.get(position).price);
                text2.setGravity(Gravity.RIGHT);
                text1.setGravity(Gravity.LEFT);
                text1.setTextSize(30);
                text2.setTextSize(30);
                /*LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)text2.getLayoutParams();
                params.setMargins(0, 0, 10, 0); //substitute parameters for left, top, right, bottom
                text2.setLayoutParams(params);
                */
                return view;
            }});

        /*setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                items));*/
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        // Modify callback to pass the selected course in callback instead of DummyContent
        mCallbacks.onItemSelected((FoodItem) listView.getItemAtPosition(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
