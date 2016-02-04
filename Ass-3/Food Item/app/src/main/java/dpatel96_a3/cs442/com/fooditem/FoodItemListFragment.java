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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class FoodItemListFragment extends ListFragment {

    private static final String TAG = "GolfcourseLIstFragment";


    private static final String STATE_ACTIVATED_POSITION = "activated_position";


    private Callbacks mCallbacks = sDummyCallbacks;


    private int mActivatedPosition = ListView.INVALID_POSITION;


    public interface Callbacks {

        public void onItemSelected(FoodItem c);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        // Modify the local dummy callback to also pass FoodItem
        public void onItemSelected(FoodItem c) {
        }
    };


    public FoodItemListFragment() {
    }

    private ArrayList<FoodItem> courses = new ArrayList<FoodItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null && getArguments().containsKey("courses")) {
            courses = getArguments().getParcelableArrayList("courses");
        }

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
                text1.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                text2.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                //text1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                //text2.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                text1.setTextSize(25);
                text2.setTextSize(25);

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


    public void setActivateOnItemClick(boolean activateOnItemClick) {

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
