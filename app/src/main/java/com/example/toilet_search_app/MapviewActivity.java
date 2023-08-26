package com.example.toilet_search_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.CalloutBalloonAdapter;
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
import java.util.HashMap;
import java.util.Map;

public class MapviewActivity extends AppCompatActivity {
    private Map<String, Map<String, Object>> total = new HashMap<>();
    private MapView mapView;

    private RelativeLayout mapViewContainer;

    private MarkerEventListener eventListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kakao_mapview);

        mapView = new MapView(this);
        mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        eventListener = new MarkerEventListener(this); // 마커 클릭 이벤트 리스너
        eventListener.total = total;

        mapView.setCalloutBalloonAdapter(new CustomBalloonAdapter(getLayoutInflater())); // 커스텀 말풍선 등록
        mapView.setPOIItemEventListener(eventListener);

        //지도 중심점,레벨(zoom) 변경/
        // 중심점 변경 - 경상대학교 칠암캠퍼스 100주년기념관
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.17962877, 128.0955306), true);
        // 줌 레벨 변경
        mapView.setZoomLevel(1, true);
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        //마커 추가/
        //마커 찍기 (경상대학교 칠암캠퍼스 100주년기념관)
        MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(35.17962877, 128.0955306);
        // 마커 아이콘 추가하는 함수
        MapPOIItem marker1 = new MapPOIItem();
        // 클릭 했을 때 나오는 호출 값
        marker1.setItemName("경상대학교 칠암캠퍼스 100주년기념관");
        // 왜 있는지 잘 모르겠음
        marker1.setTag(0);
        // 좌표를 입력받아 현 위치로 출력
        marker1.setMapPoint(MARKER_POINT1);
        //  (클릭 전)기본으로 제공하는 BluePin 마커 모양의 색.
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin);
        // (클릭 후) 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        // 지도화면 위에 추가되는 아이콘을 추가하기 위한 호출(말풍선 모양)
        mapView.addPOIItem(marker1);

        // 사용자 정의 말풍선 어댑터 생성
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        CustomBalloonAdapter customBalloonAdapter = new CustomBalloonAdapter(layoutInflater);

        // 말풍선 어댑터 설정
        mapView.setCalloutBalloonAdapter(customBalloonAdapter);

        // HTTP 통신을 통해 위도와 경도 데이터를 받아옴
        getDataFromServer();
    }

    private void getDataFromServer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // HTTP 요청 보내기
                    URL url = new URL("http://211.246.215.59:8888/abc"); // 실제 데이터를 제공하는 URL로 변경해야 합니다.
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
                String itemName = jsonObject.getString("화장실이름");
                double latitude = jsonObject.getDouble("경도");
                double longitude = jsonObject.getDouble("위도");
                String openingHours = jsonObject.getString("개방시간");
                String managementAgency = jsonObject.getString("관리기관명");
                boolean unisexToilet = jsonObject.getString("남녀공용화장실여부").equalsIgnoreCase("Y");
                int menToiletCount = Integer.parseInt(jsonObject.getString("남성용-대변기수"));
                int menUrinalCount = Integer.parseInt(jsonObject.getString("남성용-소변기수"));
                int menChildrenToiletCount = Integer.parseInt(jsonObject.getString("남성용-어린이용대변기수"));
                int menChildrenUrinalCount = Integer.parseInt(jsonObject.getString("남성용-어린이용소변기수"));
                int menDisabledToiletCount = Integer.parseInt(jsonObject.getString("남성용-장애인용대변기수"));
                int menDisabledUrinalCount = Integer.parseInt(jsonObject.getString("남성용-장애인용소변기수"));
                boolean emergencyBellInstalled = jsonObject.getString("비상벨설치여부").equalsIgnoreCase("Y");
                String emergencyBellLocation = jsonObject.getString("비상벨설치장소");
                int womenToiletCount = Integer.parseInt(jsonObject.getString("여성용-대변기수"));
                int womenChildrenToiletCount = Integer.parseInt(jsonObject.getString("여성용-어린이용대변기수"));
                int womenDisabledToiletCount = Integer.parseInt(jsonObject.getString("여성용-장애인용대변기수"));
                String locationNumberAddress = jsonObject.getString("위치번호주소");
                String locationMapAddress = jsonObject.getString("위치지도별주소");
                String phoneNumber = jsonObject.getString("전화번호");
                int toiletID = Integer.parseInt(jsonObject.getString("화장실ID"));
                boolean restroomEntranceCCTVInstalled = jsonObject.getString("화장실입구CCTV설치유무").equalsIgnoreCase("Y");


                Map<String, Object> test = new HashMap<>();
                test.put("openingHours", openingHours);

                //



                total.put(itemName, test);


                // 마커 생성
                MapPOIItem marker = new MapPOIItem();
                marker.setItemName(itemName);
                marker.setTag(i);
                marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

                marker.setUserObject(jsonObject.getString("개방시간"));

                mapView.addPOIItem(marker);
            }

            // 마커들이 모두 보이도록 카메라 이동
            //moveCameraToMarkers();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
// 커스텀 말풍선 클래스
class CustomBalloonAdapter implements CalloutBalloonAdapter {
    private View mCalloutBalloon;
    private TextView name;
    private TextView address;

    public CustomBalloonAdapter(LayoutInflater inflater) {
        mCalloutBalloon = inflater.inflate(R.layout.balloon_layout, null);
        name = mCalloutBalloon.findViewById(R.id.ball_tv_name);
        address = mCalloutBalloon.findViewById(R.id.ball_tv_address);
    }

    @Override
    public View getCalloutBalloon(MapPOIItem poiItem) {
        // 마커 클릭 시 나오는 말풍선
        name.setText(poiItem.getItemName());
        return mCalloutBalloon;
    }

    @Override
    public View getPressedCalloutBalloon(MapPOIItem poiItem) {
        // 말풍선 클릭 시
        return mCalloutBalloon;
    }
}

// 마커 클릭 이벤트 리스너
class MarkerEventListener implements MapView.POIItemEventListener {
    public Map<String, Map<String, Object>> total = new HashMap<>();
    private Context context;
    public MarkerEventListener(Context context) {
        this.context = context;
    }
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem poiItem) {
        // 마커 클릭 시
    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem poiItem) {
        // 말풍선 클릭 시 (Deprecated)
        // 이 함수도 작동하지만 그냥 아래 있는 함수에 작성하자
    }
    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem poiItem, MapPOIItem.CalloutBalloonButtonType buttonType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String[] itemList = new String[]{"화장실 정보", "취소"};
        builder.setTitle(poiItem.getItemName());
        builder.setItems(itemList, (dialog, which) -> {
            switch (which) {
                case 0:
                    // ToiletInfoActivity를 열고 필요한 데이터를 전달합니다.
                    Intent intent = new Intent(context, ToiletInfoActivity.class);
                    // 필요한 경우 인텐트에 더 많은 데이터를 추가합니다.
                    intent.putExtra("toilet_name", poiItem.getItemName());


                    intent.putExtra("toilet_address", "화장실 주소 정보를 여기에 넣으세요"); // 화장실 주소 정보 추가

                    Map<String, Object> test = total.get(poiItem.getItemName());

                    // OBJECT 형태의 변수들을 원래 형식대로 변환해주는 작업이 필요함

                    intent.putExtra("test", test.get("openingHours").toString());


                    context.startActivity(intent);
                    break;
                case 1:
                    dialog.dismiss(); // 대화상자를 닫습니다.
                    break;
            }
        });
        builder.show();
    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem poiItem, MapPoint mapPoint) {
        // 마커의 속성 중 isDraggable = true 일 때 마커를 이동시켰을 경우
    }
}