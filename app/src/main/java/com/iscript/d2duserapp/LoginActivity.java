package com.iscript.d2duserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {



    RelativeLayout rellay1, rellay2;
    Button loginbtn;

    private EditText editTextMobile;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        loginbtn = (Button)findViewById(R.id.buttonContinue);
        editTextMobile = findViewById(R.id.editTextMobile);



        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        try {
            if (!FirebaseAuth.getInstance().getCurrentUser().equals(null)){
                Intent move = new Intent(LoginActivity.this,MainActivity.class);

                startActivity(move);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent = new Intent(LoginActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }

        });
    }
}
