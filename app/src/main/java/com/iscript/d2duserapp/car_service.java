package com.iscript.d2duserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class car_service extends AppCompatActivity {
    private CardView card1,card2,card3,card4,card5,card6,card7,card8,card9;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_service);
        card1=(CardView)findViewById(R.id.card1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });

        card2=(CardView)findViewById(R.id.card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });
        card3=(CardView)findViewById(R.id.card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });
        card4=(CardView)findViewById(R.id.card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });
        card5=(CardView)findViewById(R.id.card5);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });
        card6=(CardView)findViewById(R.id.card6);
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });
        card7=(CardView)findViewById(R.id.card7);
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });
        card8=(CardView)findViewById(R.id.card8);
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });
        card9=(CardView)findViewById(R.id.card9);
        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(car_service.this,carwash.class);
                startActivity(intent);
            }
        });


    }
}
