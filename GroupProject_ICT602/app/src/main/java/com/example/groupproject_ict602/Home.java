package com.example.groupproject_ict602;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        ImageView map = (ImageView) findViewById(R.id.mapImg);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

        ImageView hazard = (ImageView) findViewById(R.id.hazardImg);

        hazard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AddHazard.class);
                startActivity(intent);
            }
        });

        ImageView news = (ImageView) findViewById(R.id.newsImg);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), HazardInfo.class);
                startActivity(intent);
            }
        });

        ImageView about = (ImageView) findViewById(R.id.aboutImg);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AboutUs.class);
                startActivity(intent);
            }
        });
    }
}
