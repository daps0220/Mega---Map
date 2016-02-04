package dpatel96_a3.cs442.com.fooditem;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by michaelHahn on 5/10/15.
 */
public class FoodModel {
    private ArrayList<FoodItem> coursesArray = new ArrayList<FoodItem>();
    private Context ctx;

    private static  String TAG = "FoodModel";

    // Initializer to read a text file into an array of golfcourse objects
    public FoodModel(Context ctx, String coursesFilename) {
        String line;
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(ctx.getAssets().open(coursesFilename)));

            while ((line = br.readLine()) != null) {
                StringTokenizer sTok = new StringTokenizer(line, ":");
                FoodItem gc = new FoodItem(sTok.nextToken());
                gc.price=sTok.nextToken();
                gc.details = sTok.nextToken();
                gc.ingrediants = sTok.nextToken();
                gc.recipe = sTok.nextToken();
                gc.id = Integer.parseInt(sTok.nextToken());
                coursesArray.add(gc);
            }
        } catch (IOException e) {
            return;
        }
    }

        // Method to retrieve courses
        public ArrayList<FoodItem> getCourses() {
            return coursesArray;
        }
}
