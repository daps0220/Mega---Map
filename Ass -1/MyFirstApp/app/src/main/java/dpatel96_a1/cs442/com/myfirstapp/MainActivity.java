package dpatel96_a1.cs442.com.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "dpatel96_a1.cs442.com.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** Called when user clicks the "SEND" ***/
    public void sendMessage(View v)
    {
        Toast.makeText(this,"Message sent from First Screen!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(this,DisplayActivity.class);
        EditText e = (EditText) findViewById(R.id.edit_message);
        String mes = e.getText().toString();
        i.putExtra(EXTRA_MESSAGE,mes);
        startActivity(i);
        Log.d("  MyFirstApp : ","Second Screen Opened.");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
