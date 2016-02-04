package dpatel96_a3.cs442.com.fooditem;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.MenuItem;



public class FoodItemDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooditem_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putParcelable("course", getIntent().getParcelableExtra("course"));
            FoodItemDetailFragment fragment = new FoodItemDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.golfcourse_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, FoodItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
