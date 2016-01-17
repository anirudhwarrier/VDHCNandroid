package project.cse16.ncerc.vdhcn;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ServerSettings.ConnectURL;
import com.ServerSettings.WebClient;


public class Registration extends ActionBarActivity {
    EditText fname, lname, dob, email, phone, zip, password, cnpword;
    Button register;
    RadioGroup genderGroup;
    RadioButton gender;
    int selectedId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        dob = (EditText) findViewById(R.id.dob);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        zip = (EditText) findViewById(R.id.zipcode);
        password = (EditText) findViewById(R.id.pword);
        cnpword = (EditText) findViewById(R.id.cpword);
        register=(Button)findViewById(R.id.register);

        genderGroup=(RadioGroup)findViewById(R.id.gender);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("".equals(fname.getText().toString())){
                    Toast.makeText(Registration.this, "Enter Your First Name", Toast.LENGTH_SHORT).show();
                }else if("".equals(lname.getText().toString())){
                    Toast.makeText(Registration.this, "Enter Your Last Name", Toast.LENGTH_SHORT).show();
                }else if("".equals(dob.getText().toString())){
                    Toast.makeText(Registration.this, "Enter Your Date of Birth", Toast.LENGTH_SHORT).show();
                }else if("".equals(email.getText().toString())){
                    Toast.makeText(Registration.this, "Enter E-Mail", Toast.LENGTH_SHORT).show();
                }else if("".equals(phone.getText().toString())){
                    Toast.makeText(Registration.this, "Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                }else if("".equals(zip.getText().toString())){
                    Toast.makeText(Registration.this, "Enter Your Postal ZIP Code", Toast.LENGTH_SHORT).show();
                }else if("".equals(password.getText().toString())){
                    Toast.makeText(Registration.this, "Enter A Password", Toast.LENGTH_SHORT).show();
                }else if("".equals(cnpword.getText().toString())){
                    Toast.makeText(Registration.this, "Confirm Password", Toast.LENGTH_SHORT).show();
                }else if(!password.getText().toString().equals(cnpword.getText().toString())){
                    Toast.makeText(Registration.this, "Password Should Match", Toast.LENGTH_SHORT).show();
                }else{
                    selectedId=genderGroup.getCheckedRadioButtonId();
                    gender=(RadioButton)findViewById(selectedId);
                    String urlStr= ConnectURL.USER_REGISTER+"FNAME="+fname.getText().toString()+"&LNAME="+lname.getText().toString()
                            +"&DOB="+dob.getText().toString()+"&GENDER="+gender.getText()+"&EMAIL="+email.getText().toString()
                            +"&PHONE="+phone.getText().toString()+"&ZIP="+zip.getText().toString()+"&PASSWORD="+password.getText().toString();
                    String resp= WebClient.GetContent(urlStr);
                    if(null!= resp && !"".equals(resp)){
                        if("1".equals(resp)){
                            Toast.makeText(Registration.this, "Registration Success! Please Login", Toast.LENGTH_SHORT).show();
                            fname.setText("");
                            lname.setText("");
                            dob.setText("");
                            email.setText("");
                            phone.setText("");
                            zip.setText("");
                            password.setText("");
                            cnpword.setText("");

                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
