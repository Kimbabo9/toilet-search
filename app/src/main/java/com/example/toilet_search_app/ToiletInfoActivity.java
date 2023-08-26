package com.example.toilet_search_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ToiletInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toilet_info);

        TextView textViewToiletName = findViewById(R.id.textViewToiletName);
        TextView textViewToiletAddress = findViewById(R.id.textViewToiletAddress);
        TextView textViewopeningHours = findViewById(R.id.textViewopeningHours);
        TextView textViewmanagementAgency = findViewById(R.id.textViewmanagementAgency);
        TextView textViewunisexToilet = findViewById(R.id.textViewunisexToilet);
        TextView textViewmenToiletCount = findViewById(R.id.textViewmenToiletCount);
        TextView textViewmenUrinalCount = findViewById(R.id.textViewmenUrinalCount);


        Intent intent = getIntent();
        if (intent != null) {
            String toiletName = intent.getStringExtra("toilet_name");
            String toiletAddress = intent.getStringExtra("toilet_address");

            String asd = intent.getStringExtra("textViewopeningHours");
            textViewopeningHours.setText(asd);

            String abc = intent.getStringExtra("textViewmanagementAgency");
            textViewmanagementAgency.setText(abc);

            String abd = intent.getStringExtra("textViewunisexToilet");
            textViewunisexToilet.setText(abd);

            String acd = intent.getStringExtra("textViewmenToiletCount");
            textViewmenToiletCount.setText(acd);

            String aad = intent.getStringExtra("textViewmenUrinalCount");
            textViewmenUrinalCount.setText(aad);


            textViewToiletName.setText(toiletName);
            textViewToiletAddress.setText(toiletAddress);
        }
    }
}
