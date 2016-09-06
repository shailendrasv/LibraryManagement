package in.ac.mmmut.librarymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import in.ac.mmmut.librarymanagement.admin.LibrarianUser;


public class FirstPage extends AppCompatActivity {

    private Button stud,lib;
    private static final long delay = 2000L;
    private boolean mRecentlyBackPressed = false;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {

        @Override
        public void run() {
            mRecentlyBackPressed=false;
        }
    };
    public void studAuth(){
        stud=(Button)findViewById(R.id.std_button);
        stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpage=new Intent(FirstPage.this,Login.class);
                startActivity(fpage);
            }
        });

    }

    public void libAuth(){
        lib=(Button)findViewById(R.id.lib_button);
        lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpage3=new Intent(FirstPage.this,in.ac.mmmut.librarymanagement.admin.LibrarianUser.class);
                startActivity(fpage3);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        studAuth();
        libAuth();
    }
    @Override
    public void onBackPressed() {

        //add this condition if (doubleBackToExitPressedOnce || fragmentManager.getBackStackEntryCount() != 0) // in case of Fragment-based add
        if (mRecentlyBackPressed) {
            mExitHandler.removeCallbacks(mExitRunnable);
            mExitHandler = null;
            super.onBackPressed();
        }
        else
        {
            mRecentlyBackPressed = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            mExitHandler.postDelayed(mExitRunnable, delay);
        }
    }
}
