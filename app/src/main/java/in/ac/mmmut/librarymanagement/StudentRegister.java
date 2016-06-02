package in.ac.mmmut.librarymanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


public class StudentRegister extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_register);
        final EditText stname = (EditText) findViewById(R.id.stName);
        final EditText stroll = (EditText) findViewById(R.id.stRoll);
        final EditText stbranch = (EditText) findViewById(R.id.stBranch);
        final EditText styear = (EditText) findViewById(R.id.stYear);
        final EditText stemail = (EditText) findViewById(R.id.stEmail);
        final EditText stphone = (EditText) findViewById(R.id.stPhone);
        final EditText stusername = (EditText) findViewById(R.id.stUsername);
        final EditText stpassword = (EditText) findViewById(R.id.stPassword);
        final EditText stcnfrmpassword = (EditText) findViewById(R.id.stCnfrmPass);
        final Button stregister = (Button) findViewById(R.id.stRegister);
    }
}
