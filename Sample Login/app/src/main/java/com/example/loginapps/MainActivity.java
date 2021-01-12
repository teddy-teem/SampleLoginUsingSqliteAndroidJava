package com.example.loginapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
EditText userID, userPassword;
Button login, forgetPass;
TextView newUser;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
DBManagment myDB = new DBManagment(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userID = findViewById(R.id.userId);
        userPassword = findViewById(R.id.userPassword);
        login = findViewById(R.id.login);
        newUser = findViewById(R.id.newUser);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userID.getText().toString();
                String pass = userPassword.getText().toString();

                if(user.equals("")|| pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean chekuserpass = myDB.checkusernamepassword(user, pass);
                    if(chekuserpass==true){
                        Toast.makeText(MainActivity.this, "You are In", Toast.LENGTH_SHORT).show();
                        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                        Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                        intent.putExtra("Date", currentDate);
                        intent.putExtra("Time", currentTime);
                        intent.putExtra("userN", user);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Wrong Id/Pass", Toast.LENGTH_SHORT).show();
                    }
                }
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
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
}