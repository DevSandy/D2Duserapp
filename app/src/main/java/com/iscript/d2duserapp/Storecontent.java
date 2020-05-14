package com.iscript.d2duserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Storecontent extends AppCompatActivity {

    private FirebaseAuth mauth;
    private CardView foodprovisions,dairy_egg,breakfast,instantfood,chocolate,beverage,snacks,care,coldstore,cleaning,condiments,bestseller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storecontent);

        foodprovisions=(CardView)findViewById(R.id.provisions);
        dairy_egg=(CardView)findViewById(R.id.eggs);
        breakfast=(CardView)findViewById(R.id.breakfast);
        instantfood=(CardView)findViewById(R.id.instantfood);
        chocolate=(CardView)findViewById(R.id.chocolate);
        beverage=(CardView)findViewById(R.id.beverage);
        snacks=(CardView)findViewById(R.id.snacks);
        care=(CardView)findViewById(R.id.care);
        coldstore=(CardView)findViewById(R.id.coldstore);
        cleaning=(CardView)findViewById(R.id.cleaning);
        condiments=(CardView)findViewById(R.id.condiments);
        bestseller=(CardView)findViewById(R.id.bestseller);

        mauth = FirebaseAuth.getInstance();

        FirebaseUser currentuser = mauth.getCurrentUser();
        final String uid = currentuser.getUid();






        Intent i=this.getIntent();
        final String name=i.getExtras().getString("NAME_KEY");
        final String description=i.getExtras().getString("DESCRIPTION_KEY");
        final String imageURL=i.getExtras().getString("IMAGE_KEY");
        final String childkey=i.getExtras().getString("CHILD_KEY");


        foodprovisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("uid",uid);

                intent.putExtra("catogery","foodprovision");
                startActivity(intent);
            }
        });

        dairy_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("uid",uid);

                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","dairyEgg");
                startActivity(intent);
            }
        });
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);

                intent.putExtra("uid",uid);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","breakfast");
                startActivity(intent);
            }
        });
        instantfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","instantFood");
                startActivity(intent);
            }
        });
        chocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("uid",uid);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","chocolate");
                startActivity(intent);
            }
        });
        beverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","beverage");
                startActivity(intent);
            }
        });
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","snacks");
                startActivity(intent);
            }
        });
        care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("uid",uid);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","care");
                startActivity(intent);
            }
        });
        coldstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("uid",uid);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","coldstore");
                startActivity(intent);
            }
        });
        cleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("uid",uid);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","cleaning");
                startActivity(intent);
            }
        });
        condiments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("uid",uid);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","condiments");
                startActivity(intent);
            }
        });
        bestseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecontent.this, grocerylistactivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("NAME_KEY",name);
                intent.putExtra("DESCRIPTION_KEY",description);
                intent.putExtra("IMAGE_KEY",imageURL);
                intent.putExtra("CHILD_KEY",childkey);
                intent.putExtra("catogery","bestseller");
                startActivity(intent);
            }
        });

    }
}
