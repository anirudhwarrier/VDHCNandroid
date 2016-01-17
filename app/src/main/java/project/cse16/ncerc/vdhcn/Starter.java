package project.cse16.ncerc.vdhcn;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ServerSettings.ConnectURL;
import com.ServerSettings.WebClient;

import DatabaseConnection.DbCon;

public class Starter extends ActionBarActivity {
    EditText euname,epword;
    Button Login;
    TextView register;
    String un,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        /**For Network**/
         if(Build.VERSION.SDK_INT>=9){
             StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder()
                     .permitAll().build();
             StrictMode.setThreadPolicy(policy);
         }
        /**For network end**/
        euname=(EditText)findViewById(R.id.username);
        epword=(EditText)findViewById(R.id.password);
        register=(TextView)findViewById(R.id.regclick);
        Login=(Button)findViewById(R.id.loginbt);
        try {
            DbCon.context=getApplicationContext();
            DbCon.init();
            String sql="select * from login";
            Cursor cu=DbCon.getData(sql);
            if(cu!= null && cu.moveToNext()){
                System.out.println("cu.getString(0) = " + cu.getString(0));
                System.out.println("cu.getString(1) = " + cu.getString(1));
                Intent intent=new Intent(Starter.this,Home.class);
                startActivity(intent);
                Starter.this.finish();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(euname.getText().toString())){
                   Toast.makeText(Starter.this,"Enter Your Username",Toast.LENGTH_LONG).show();
                }else if("".equals(epword.getText().toString())){
                    Toast.makeText(Starter.this,"Enter Password",Toast.LENGTH_LONG).show();
                }else{
                 String urlStr=ConnectURL.LOGIN+"USERNAME="+euname.getText().toString()+
                         "&PASSWORD="+epword.getText().toString();
                 String resp=WebClient.GetContent(urlStr);
                    if(null!= resp && !"".equals(resp)){
                        if("1".equals(resp)){
                            String sql="insert into login values('"+euname.getText()+"','"+epword.getText().toString()+"')";
                            DbCon.context=getApplicationContext();
                            try {
                                DbCon.init();
                                int i=DbCon.putData(sql);
                                System.out.println("i = " + i);
                                if(i>0){
                                    Intent intent=new Intent(Starter.this,Home.class);
                                    startActivity(intent);
                                    Starter.this.finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if("2".equals(resp)){
                            Toast.makeText(Starter.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            euname.setText("");
                            epword.setText("");
                        }
                    }else{
                        Toast.makeText(Starter.this, "Connection Error!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Starter.this,Registration.class);
                startActivity(intent);
//                Starter.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
           intent=new Intent(Starter.this,About.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
