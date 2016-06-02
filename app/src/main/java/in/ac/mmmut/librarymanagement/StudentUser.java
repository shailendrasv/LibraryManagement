package in.ac.mmmut.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class StudentUser extends AppCompatActivity {

    private Button stReg;
    public void studReg(){
        stReg=(Button)findViewById(R.id.stud_register);
        stReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regst=new Intent(StudentUser.this,StudentRegister.class);
                startActivity(regst);
            }
        });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_user);
        studReg();
    }
}
