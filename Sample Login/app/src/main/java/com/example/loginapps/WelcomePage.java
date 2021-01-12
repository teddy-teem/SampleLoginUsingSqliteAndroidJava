package com.example.loginapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity {
    TextView time, date, user;
    Button logout;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        time = (TextView) findViewById(R.id.logintime);
        date = (TextView) findViewById(R.id.logindate);
        user = (TextView) findViewById(R.id.currentuser);
        logout = (Button) findViewById(R.id.logout);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null){
            String Time = (String)b.get("Time");
            String Date = (String)b.get("Date");
            String userId = (String)b.get("userN");

            time.setText(Time);
            date.setText(Date);
            user.setText(userId);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to back", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
}