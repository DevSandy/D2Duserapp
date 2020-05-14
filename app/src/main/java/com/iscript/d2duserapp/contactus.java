package com.iscript.d2duserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class contactus extends AppCompatActivity {
    private ImageView dcall,dmail,dwp,dloc,mcall,mloc,mwp,mmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


        dcall = (ImageView)findViewById(R.id.dcall);
        dmail= (ImageView)findViewById(R.id.dmsg);
        dwp = (ImageView)findViewById(R.id.dwp);
        dloc = (ImageView)findViewById(R.id.dloc);
        mcall = (ImageView)findViewById(R.id.mcall);
        mmail =(ImageView)findViewById(R.id.mmail);
        mwp = (ImageView)findViewById(R.id.mwp);
        mloc = (ImageView)findViewById(R.id.mloc);


        dcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9964930023"));
                startActivity(intent);

            }
        });
        dmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","d2dservicesforyour@email.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Re: quirey");
                intent.putExtra(Intent.EXTRA_TEXT, "Your message goes here......");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }
        });
        dwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://wa.me/919964930023";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });
        dloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.google.com/maps/place/13%C2%B004'27.1%22N+77%C2%B030'13.6%22E/@13.0753851,77.5015691,17z/data=!4m5!3m4!1s0x0:0x0!8m2!3d13.0742042!4d77.50379?hl=en";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });
        mcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8660225160"));
                startActivity(intent);
            }
        });
        mmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","devsandy12@email.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Re: quirey");
                intent.putExtra(Intent.EXTRA_TEXT, "Your message goes here......");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
        mwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://wa.me/919035918448";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        mloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.google.com/maps/place/13%C2%B004'27.1%22N+77%C2%B030'13.6%22E/@13.0753851,77.5015691,17z/data=!4m5!3m4!1s0x0:0x0!8m2!3d13.0742042!4d77.50379?hl=en";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }
}
