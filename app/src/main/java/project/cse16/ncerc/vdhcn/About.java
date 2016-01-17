package project.cse16.ncerc.vdhcn;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class About extends ActionBarActivity {
    int versioncode;
    String versionName;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        versioncode=BuildConfig.VERSION_CODE;
        versionName=BuildConfig.VERSION_NAME;
        System.out.println("versioncode = " + versioncode);
        System.out.println("versionName = " + versionName);
        tv1=(TextView)findViewById(R.id.versioncode);
        tv2=(TextView)findViewById(R.id.versionname);
        tv1.setText(String.valueOf(versioncode));
        tv2.setText(versionName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
