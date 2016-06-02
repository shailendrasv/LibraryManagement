package in.ac.mmmut.librarymanagement;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class NotificationMessage extends AppCompatActivity{

    private BroadcastReceiver notifRegBroadcastReceiver;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private ProgressBar spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);
        spinner=(ProgressBar)findViewById(R.id.progressBar);

        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute();

        notifRegBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //check type of intent filter
                if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCCESS))
                {
                    //Registration Success
                    String token=intent.getStringExtra("token");
                    Toast.makeText(getApplicationContext(),"App ready to use"/*+token*/,Toast.LENGTH_LONG).show();

                }else if(intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_ERROR))
                {
                    //Registration Error
                    Toast.makeText(getApplicationContext(),"Device Registration error !!!",Toast.LENGTH_LONG).show();

                }else
                {
                    Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_LONG).show();

                }
            }
        };
        //check status of google play service in device
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if(ConnectionResult.SUCCESS !=resultCode)
        {

            if (apiAvailability.isUserResolvableError(resultCode)) {
                Toast.makeText(getApplicationContext(),"Google Play Service is not installed/enabled in this device !!!",Toast.LENGTH_LONG).show();
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),"This device does not support Google Play Services",Toast.LENGTH_LONG).show();
                finish();
            }
            //type of error

        }else
        {
            //start service
            Intent intent=new Intent(this,GCMRegistrationIntentService.class);
            startService(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("NotificationMessage","onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(notifRegBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(notifRegBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("NotificationMessage","onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notifRegBroadcastReceiver);
    }


    private class BackgroundTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {

            spinner.setVisibility(View.VISIBLE);
        }



        @Override
        protected Void doInBackground(Void... params) {
           /* Thread splashThread = new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(10000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            splashThread.start();*/

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            spinner.setVisibility(View.INVISIBLE);
            Intent startMainScreen = new Intent(getApplicationContext(), FirstPage.class);
            startActivity(startMainScreen);
            finish();

        }
    }


}
