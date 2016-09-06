package in.ac.mmmut.librarymanagement;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends AppCompatActivity {

    EditText name,roll_no ,password, email,branch,year,phone,username,cnfpass;
    String Name, Password, Email,Roll_no,Branch,Year,Phone,Username,Cnfpass;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.stName);
        roll_no = (EditText) findViewById(R.id.stRoll);
        branch = (EditText) findViewById(R.id.stBranch);
        year = (EditText) findViewById(R.id.stYear);
        email = (EditText) findViewById(R.id.stEmail);
        phone = (EditText) findViewById(R.id.stPhone);
        username = (EditText) findViewById(R.id.stUsername);
        password= (EditText) findViewById(R.id.stPassword);
        cnfpass=(EditText)findViewById(R.id.stCnfrmPass);

    }

    public void register_register(View v){


        if (  ( !username.getText().toString().equals("")) && ( !password.getText().toString().equals(""))
                && ( !roll_no.getText().toString().equals(""))  && ( !name.getText().toString().equals(""))
                && ( !year.getText().toString().equals(""))   && ( !email.getText().toString().equals(""))
                && ( !phone.getText().toString().equals(""))  && ( !branch.getText().toString().equals(""))
                && ( !cnfpass.getText().toString().equals("")))
        {
            Name = name.getText().toString();
            Roll_no = roll_no.getText().toString();
            Branch = branch.getText().toString();
            Year = year.getText().toString();
            Email = email.getText().toString();
            Phone = phone.getText().toString();
            Username = username.getText().toString();
            Password = password.getText().toString();
            Cnfpass=cnfpass.getText().toString();
            if(Cnfpass.equals(Password))
            {
                BackGround b = new BackGround();
                b.execute(Name, Roll_no, Branch, Year, Email, Phone, Username, Password);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_LONG).show();
            }


        }else
        {
            Toast.makeText(getApplicationContext(),"Empty fields are not allowed",Toast.LENGTH_LONG).show();
        }
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String roll_no=params[1];
            String branch=params[2];
            String year=params[3];
            String email=params[4];
            String phone=params[5];
            String username=params[6];
            String password=params[7];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://libraryphp-shailu.rhcloud.com/register.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String datav= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("roll_no","UTF-8")+"="+URLEncoder.encode(roll_no,"UTF-8")+"&"+
                        URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+
                        URLEncoder.encode("year","UTF-8")+"="+URLEncoder.encode(year,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bw.write(datav);
                bw.flush();
                bw.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("success")){
                s="You have succesfully registered!!!";
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            }
        }
    }

}