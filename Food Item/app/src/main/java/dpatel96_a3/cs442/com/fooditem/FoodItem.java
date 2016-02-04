package dpatel96_a3.cs442.com.fooditem;

import android.os.Parcel;
import android.os.Parcelable;


public class FoodItem implements Parcelable {
    // Golf course attributes
    String  name = "None";
    String  details = "None";
    String ingrediants = "None";
    String recipe = "None";
    String price = "None";
    int id =0;
    boolean isPublic;

    // Constructor for course
    FoodItem(String name) {
        this.name = name;
    }
    // Text representation of the class
    public String toString () {
        String s =String.format("%2d. %-15s", id, name);
        return s;
    }
    public String toPrice(){

        return price;
    }

    // Parcelable implementation
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(id);
        out.writeString(price);
        out.writeString(details);
        out.writeString(ingrediants);
        out.writeString(recipe);
    }
    public static final Parcelable.Creator<FoodItem> CREATOR
            = new Parcelable.Creator<FoodItem>() {
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
    private FoodItem(Parcel in) {
        name = in.readString();
        id =in.readInt();
        price =in.readString();
        details=in.readString();
        ingrediants = in.readString();
        recipe = in.readString();
    }
}
