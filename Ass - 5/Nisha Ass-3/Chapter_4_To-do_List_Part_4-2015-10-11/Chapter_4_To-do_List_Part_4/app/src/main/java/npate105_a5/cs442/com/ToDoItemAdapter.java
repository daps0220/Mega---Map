package npate105_a5.cs442.com;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import npate105_a5.cs442.com.R;
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

  int resource;
  public static int count =0;
  List<Integer> i;

  SharedPreferences sharedpreferences;
  public static final String MyPREFERENCES = "MyPrefs" ;
  public static final String values = "valueKey";
  public static final String date = "dateKey";
  public static final String id = "idKey";

  public ToDoItemAdapter(Context context,
                         int resource,
                         List<ToDoItem> items) {
    super(context, resource, items);
    this.resource = resource;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LinearLayout todoView;


    sharedpreferences = getContext().getSharedPreferences(MyPREFERENCES+position,0);

    ToDoItem item = getItem(position);

    String taskString = item.getTask();
    Date createdDate = item.getCreated();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    String dateString = sdf.format(createdDate);

    if (convertView == null) {
      todoView = new LinearLayout(getContext());
      String inflater = Context.LAYOUT_INFLATER_SERVICE;
      LayoutInflater li;
      li = (LayoutInflater)getContext().getSystemService(inflater);
      li.inflate(resource, todoView, true);
    } else {
      todoView = (LinearLayout) convertView;
    }

    TextView dateView = (TextView)todoView.findViewById(R.id.rowDate);
    TextView taskView = (TextView)todoView.findViewById(R.id.row);



    dateView.setText(dateString);
    taskView.setText(taskString);

      SharedPreferences.Editor editor = sharedpreferences.edit();

      editor.putString(values+position, taskView.getText().toString());
    editor.putString(date + position, dateView.getText().toString());
   count=position;
    Log.d("huhhh1",count+"");
    //editor.putString(Email, e);
    editor.clear();
      editor.commit();

    return todoView;
  }

}