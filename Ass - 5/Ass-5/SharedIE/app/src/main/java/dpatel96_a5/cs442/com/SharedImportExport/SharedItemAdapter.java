package dpatel96_a5.cs442.com.SharedImportExport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SharedItemAdapter extends ArrayAdapter<SharedItem> {

  int resource;

  public SharedItemAdapter(Context context,
                           int resource,
                           List<SharedItem> items) {
    super(context, resource, items);
    this.resource = resource;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LinearLayout todoView;
    SharedItem item = getItem(position);

    String taskString = item.getNum()+".    "+item.getTask();
    Date createdDate = item.getCreated();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
    String dateString = sdf.format(createdDate);
    int num = item.getNum();
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
    TextView NumView = (TextView)todoView.findViewById(R.id.rowNum);

    dateView.setText(dateString);
    taskView.setText(taskString);
    NumView.setText(Integer.toString(num));

    return todoView;
  }
}