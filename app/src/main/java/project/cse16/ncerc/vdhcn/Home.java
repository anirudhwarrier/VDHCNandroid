package project.cse16.ncerc.vdhcn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ServerSettings.ConnectURL;
import com.ServerSettings.WebClient;

import java.util.ArrayList;
import java.util.HashMap;


public class Home extends ActionBarActivity {
    ListView listView;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ListView listView = (ListView) findViewById(R.id.listView);
        //listView.setAdapter(null);
        String[] temp;

        //ArrayAdapter arrayAdapter = new ArrayAdapter(Home.this, android.R.layout.simple_list_item_1,list);
        SimpleAdapter arrayAdapter = new SimpleAdapter(Home.this,list, R.layout.view_item,new String[]{"VideoName"},new int[]{R.id.textViewVideoName});
        //listView.clearChoices();
        String urlStr= "";
        urlStr = ConnectURL.GETVIDEO;
        String resp = WebClient.GetContent(urlStr);
        list.clear();
        if(resp!=null);
        {
            temp = resp.split(";");
            for (int i = 0; i < temp.length; i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                String[] temp2 = temp[i].split(",");
                //map.put("VideoID",temp2[0]);
                map.put("VideoName", temp2[1] + " ");
                map.put("VideoPath", temp2[2] + " ");
                list.add(map);
            }
            listView.setAdapter(arrayAdapter);
        }
        //final HashMap<String, String> finalMap = map;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
                //String value = (String)parent.getItemAtPosition(position).toString();
                String value2 = map.get("VideoPath").trim();
                Intent i = new Intent(Home.this, VideoPlay.class);
                i.putExtra("VIDEO_PATH", value2);
                startActivity(i);

                Toast.makeText(getBaseContext(), value2, Toast.LENGTH_LONG).show();


            }
        });
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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
