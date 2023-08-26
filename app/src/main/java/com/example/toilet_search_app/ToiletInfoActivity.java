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
        TextView textViewmenChildrenToiletCount = findViewById(R.id.textViewmenChildrenToiletCount);
        TextView textViewmenChildrenUrinalCount = findViewById(R.id.textViewmenChildrenUrinalCount);
        TextView textViewmenDisabledToiletCount = findViewById(R.id.textViewmenDisabledToiletCount);
        TextView textViewmenDisabledUrinalCount = findViewById(R.id.textViewmenDisabledUrinalCount);
        TextView textViewemergencyBellInstalled = findViewById(R.id.textViewemergencyBellInstalled);
        TextView textViewemergencyBellLocation = findViewById(R.id.textViewemergencyBellLocation);
        TextView textViewwomenToiletCount = findViewById(R.id.textViewwomenToiletCount);
        TextView textViewwomenChildrenToiletCount = findViewById(R.id.textViewwomenChildrenToiletCount);
        TextView textViewwomenDisabledToiletCount = findViewById(R.id.textViewwomenDisabledToiletCount);
        TextView textViewlocationNumberAddress = findViewById(R.id.textViewlocationNumberAddress);
        TextView textViewlocationMapAddress = findViewById(R.id.textViewlocationMapAddress);
        TextView textViewphoneNumber = findViewById(R.id.textViewphoneNumber);
        TextView textViewtoiletID = findViewById(R.id.textViewtoiletID);
        TextView textViewrestroomEntranceCCTVInstalled = findViewById(R.id.textViewrestroomEntranceCCTVInstalled);


        Intent intent = getIntent();
        if (intent != null) {
            String toiletName = intent.getStringExtra("toilet_name");


            String hours = intent.getStringExtra("textViewopeningHours");
            textViewopeningHours.setText(hours);

            String manage = intent.getStringExtra("textViewmanagementAgency");
            textViewmanagementAgency.setText(manage);

            String unisex = intent.getStringExtra("textViewunisexToilet");
            textViewunisexToilet.setText(unisex);

            String mentoilet = intent.getStringExtra("textViewmenToiletCount");
            textViewmenToiletCount.setText(mentoilet);

            String menurinal = intent.getStringExtra("textViewmenUrinalCount");
            textViewmenUrinalCount.setText(menurinal);

            String chlidrentoilet = intent.getStringExtra("textViewmenChildrenToiletCount");
            textViewmenChildrenToiletCount.setText(chlidrentoilet);

            String childrenurinal = intent.getStringExtra("textViewmenChildrenUrinalCount");
            textViewmenChildrenUrinalCount.setText(childrenurinal);

            String mendisabled = intent.getStringExtra("textViewmenDisabledToiletCount");
            textViewmenDisabledToiletCount.setText(mendisabled);

            String mendisableduri = intent.getStringExtra("textViewmenDisabledUrinalCount");
            textViewmenDisabledUrinalCount.setText(mendisableduri);

            String emergencybell = intent.getStringExtra("textViewemergencyBellInstalled");
            textViewemergencyBellInstalled.setText(emergencybell);

            String emergencybelllo = intent.getStringExtra("textViewemergencyBellLocation");
            textViewemergencyBellLocation.setText(emergencybelllo);

            String womenToiletCount = intent.getStringExtra("textViewwomenToiletCount");
            textViewwomenToiletCount.setText(womenToiletCount);

            String womenChildrenToiletCount = intent.getStringExtra("textViewwomenChildrenToiletCount");
            textViewwomenChildrenToiletCount.setText(womenChildrenToiletCount);

            String womenDisabledToiletCount = intent.getStringExtra("textViewwomenDisabledToiletCount");
            textViewwomenDisabledToiletCount.setText(womenDisabledToiletCount);

            String locationNumberAddress = intent.getStringExtra("textViewlocationNumberAddress");
            textViewlocationNumberAddress.setText(locationNumberAddress);

            String locationMapAddress = intent.getStringExtra("textViewlocationMapAddress");
            textViewlocationMapAddress.setText(locationMapAddress);

            String phoneNumber = intent.getStringExtra("textViewphoneNumber");
            textViewphoneNumber.setText(phoneNumber);

            String toiletID = intent.getStringExtra("textViewtoiletID");
            textViewtoiletID.setText(toiletID);

            String restroomEntranceCCTVInstalled = intent.getStringExtra("textViewrestroomEntranceCCTVInstalled");
            textViewrestroomEntranceCCTVInstalled.setText(restroomEntranceCCTVInstalled);


            textViewToiletName.setText(toiletName);
            textViewToiletAddress.setText(toiletAddress);
        }
    }
}

