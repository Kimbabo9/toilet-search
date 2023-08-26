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
        TextView textViewTest = findViewById(R.id.textViewTest);

        Intent intent = getIntent();
        if (intent != null) {
            String toiletName = intent.getStringExtra("toilet_name");
            String toiletAddress = intent.getStringExtra("toilet_address");

            String asd = intent.getStringExtra("textViewTest");
            textViewTest.setText(asd);


            textViewToiletName.setText(toiletName);
            textViewToiletAddress.setText(toiletAddress);
        }
    }
}