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
            textViewToiletName.setText("화장실 이름 : " + toiletName);


            String hours = intent.getStringExtra("textViewopeningHours");
            textViewopeningHours.setText("개방시간 : " + hours);

            String manage = intent.getStringExtra("textViewmanagementAgency");
            textViewmanagementAgency.setText("관리기관명 : " + manage);

            String unisex = intent.getStringExtra("textViewunisexToilet");
            textViewunisexToilet.setText("남녀공용화장실여부 : " + unisex);

            String mentoilet = intent.getStringExtra("textViewmenToiletCount");
            textViewmenToiletCount.setText("남성용-대변기수 : " + mentoilet);

            String menurinal = intent.getStringExtra("textViewmenUrinalCount");
            textViewmenUrinalCount.setText("남성용-소변기수 : " + menurinal);

            String chlidrentoilet = intent.getStringExtra("textViewmenChildrenToiletCount");
            textViewmenChildrenToiletCount.setText("남성용-어린이용대변기수 : " + chlidrentoilet);

            String childrenurinal = intent.getStringExtra("textViewmenChildrenUrinalCount");
            textViewmenChildrenUrinalCount.setText("남성용-어린이용소변기수 : " + childrenurinal);

            String mendisabled = intent.getStringExtra("textViewmenDisabledToiletCount");
            textViewmenDisabledToiletCount.setText("남성용-장애인용대변기수 : " + mendisabled);

            String mendisableduri = intent.getStringExtra("textViewmenDisabledUrinalCount");
            textViewmenDisabledUrinalCount.setText("남성용-장애인용소변기수 : " + mendisableduri);

            String emergencybell = intent.getStringExtra("textViewemergencyBellInstalled");
            textViewemergencyBellInstalled.setText("비상벨설치여부 : " + emergencybell);

            String emergencybelllo = intent.getStringExtra("textViewemergencyBellLocation");
            textViewemergencyBellLocation.setText("비상벨설치장소 : " + emergencybelllo);

            String womenToiletCount = intent.getStringExtra("textViewwomenToiletCount");
            textViewwomenToiletCount.setText("여성용-대변기수 : " + womenToiletCount);

            String womenChildrenToiletCount = intent.getStringExtra("textViewwomenChildrenToiletCount");
            textViewwomenChildrenToiletCount.setText("여성용-어린이용대변기수 : " + womenChildrenToiletCount);

            String womenDisabledToiletCount = intent.getStringExtra("textViewwomenDisabledToiletCount");
            textViewwomenDisabledToiletCount.setText("여성용-장애인용대변기수 : " + womenDisabledToiletCount);

            String locationNumberAddress = intent.getStringExtra("textViewlocationNumberAddress");
            textViewlocationNumberAddress.setText("위치번호주소 : " + locationNumberAddress);

            String locationMapAddress = intent.getStringExtra("textViewlocationMapAddress");
            textViewlocationMapAddress.setText("위치지도별주소 : " + locationMapAddress);

            String phoneNumber = intent.getStringExtra("textViewphoneNumber");
            textViewphoneNumber.setText("전화번호 : " + phoneNumber);

            String toiletID = intent.getStringExtra("textViewtoiletID");
            textViewtoiletID.setText("화장실ID : " + toiletID);

            String restroomEntranceCCTVInstalled = intent.getStringExtra("textViewrestroomEntranceCCTVInstalled");
            textViewrestroomEntranceCCTVInstalled.setText("화장실입구CCTV설치유무 : " + restroomEntranceCCTVInstalled);



        }
    }
}

