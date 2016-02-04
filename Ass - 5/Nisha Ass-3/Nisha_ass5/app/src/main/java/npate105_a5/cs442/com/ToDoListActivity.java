package npate105_a5.cs442.com;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import npate105_a5.cs442.com.R;

public class ToDoListActivity extends Activity implements NewItemFragment.OnNewItemAddedListener {

  private ArrayList<ToDoItem> todoItems;
  private ToDoItemAdapter aa;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences  sharedPreferences12;
    public static final String values = "valueKey";
    public static final String date = "dateKey";
    public static final String id = "idKey";
    ToDoListFragment todoListFragment;
    public int c =0;


  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inflate your view
    setContentView(R.layout.main);
    // Get references to the Fragments
    FragmentManager fm = getFragmentManager();
    todoListFragment =
      (ToDoListFragment)fm.findFragmentById(R.id.TodoListFragment);

    // Create the array list of to do items
    todoItems = new ArrayList<ToDoItem>();

    // Create the array adapter to bind the array to the ListView
    int resID = R.layout.todolist_item;
    aa = new ToDoItemAdapter(this, resID, todoItems);


    // Bind the array adapter to the ListView.
    todoListFragment.setListAdapter(aa);

  }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int resID = R.layout.todolist_item;
        aa = new ToDoItemAdapter(this, resID, todoItems);


        // Bind the array adapter to the ListView.
        todoListFragment.setListAdapter(aa);


    }

    public void onNewItemAdded(String newItem) {
    ToDoItem newTodoItem = new ToDoItem(newItem);
    todoItems.add(0, newTodoItem);
    aa.notifyDataSetChanged();
        c++;
        Log.d("count>>", c + "");

  }


    //This will created shared prefers

  public void saveClicked(View view){
      try{
          if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
              //handle case of no SDCARD present
              Toast.   makeText(this, "No SDCARD Fonud!!!", Toast.LENGTH_LONG).show();
          }else{
              {
                  String dir = Environment.getExternalStorageDirectory() + File.separator + "Hello";
                  //create folder
                  File folder = new File(dir); //folder name
                  if (!folder.exists()) {
                      folder.mkdirs();
                  }else{
                      resetClicked(view);
                  }

                  //create file
                  File file = new File(dir, "sp.txt");
                  file.createNewFile();
                  Toast.   makeText(this, "File Created!!!", Toast.LENGTH_SHORT).show();
                  SharedPreferences settings;

                  FileWriter writer = new FileWriter(file);

                  for(int j = 1; j<= c;j++) {
                      settings = getSharedPreferences(MyPREFERENCES + (j-1), 0); //1
                      //String text1 = settings.getString(values + 0, null);
                      //String text2 = settings.getString(date + 0, null);//2
                      writer.append(settings.getString(values + (j-1), null) + "\t");
                      writer.append(settings.getString(date + (j-1), null) + "\n");
                  }
                  writer.flush();
                  writer.close();

                  Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
              }
          }
      }catch(Exception e){
          Log.e("Error",e+"");
      }

  }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void resetClicked(View view){

    SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES,0);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    editor.clear();
    editor.commit();
       try{
           String dir = Environment.getExternalStorageDirectory() + File.separator + "Hello";
           //create folder
           File folder = new File(dir); //folder name
           if(folder.exists()){
               folder.delete();
           }

       }catch(Exception e){
           Log.e("DEleteing File>>", e+"");
       }


        todoItems.clear();

        aa.notifyDataSetChanged();

  }


}