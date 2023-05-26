package com.example.toilet_search_app;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapviewActivity extends AppCompatActivity {

    private MapView mapView;
    private RelativeLayout mapViewContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kakao_mapview);

        mapView = new MapView(this);
        mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // HTTP 통신을 통해 위도와 경도 데이터를 받아옴
        getDataFromServer();
    }

    private void getDataFromServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // HTTP 요청 보내기
                    URL url = new URL("http://backend.coderoid.kro.kr:3000/tour"); // 실제 데이터를 제공하는 URL로 변경해야 합니다.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    // 응답 데이터 읽기
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder responseData = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseData.append(line);
                    }
                    reader.close();

                    // 데이터 파싱 및 마커 찍기
                    parseDataAndDrawMarkers(responseData.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void parseDataAndDrawMarkers(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");

                // 마커 생성
                MapPOIItem marker = new MapPOIItem();
                marker.setItemName("Marker " + i);
                marker.setTag(i);
                marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                mapView.addPOIItem(marker);
            }

            // 마커들이 모두 보이도록 카메라 이동
            //moveCameraToMarkers();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*private void moveCameraToMarkers() {
        MapPointBounds.Builder boundsBuilder = new MapPointBounds.Builder();
        MapPOIItem[] markers = mapView.getPOIItems();
        for (MapPOIItem marker : markers) {
            boundsBuilder.include(marker.getMapPoint());
        }

        int padding = 100; // 마커 주변에 여유 공간
        MapPointBounds bounds = boundsBuilder.build();
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(bounds, padding));
    }*/
}