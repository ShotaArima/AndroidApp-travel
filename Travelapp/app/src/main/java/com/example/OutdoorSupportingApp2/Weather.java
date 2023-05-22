package com.example.OutdoorSupportingApp2;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Weather extends AppCompatActivity {

    private static final String DEBUG_TAG = "MYAPP_Weather";
    private static final String WEATHERINFO_URL = "https://api.openweathermap.org/data/2.5/weather?lang=ja";
    private static final String APP_ID = "117409bd4752187fb2065e98423d25bf";
    private List<Map<String, String>> _list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        _list  = createList();

        ListView lvCityList = findViewById(R.id.Weather_City_LV);
        String[] from = {"name"};
        int[] to = {android.R.id.text1};
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), _list, android.R.layout.simple_list_item_1, from, to);
        lvCityList.setAdapter(adapter);
        lvCityList.setOnItemClickListener(new ListItemClickListener());

        //戻るボタン
        Button BackButton = findViewById(R.id.Mail_Back_Button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //都市リスト
    private List<Map<String, String>> createList() {
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("name","札幌/Sapporo");
        map.put("q","Sapporo");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "青森/Aomori");
        map.put("q", "Aomori");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "盛岡/Morioka");
        map.put("q","Morioka");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "秋田/Akita");
        map.put("q","Akita");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "仙台/Sendai");
        map.put("q","Sendai");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "山形/Yamagata");
        map.put("q","Yamagata");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "福島");
        map.put("q","Fukushima");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "茨城_水戸");
        map.put("q","Mito");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "栃木_宇都宮");
        map.put("q","Utsunomiya");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "群馬_前橋");
        map.put("q","Maebashi");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "埼玉");
        map.put("q","Saitama");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "千葉");
        map.put("q","Tiba");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "東京");
        map.put("q","Tokyo");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "神奈川_横浜");
        map.put("q","Yokohama");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "新潟");
        map.put("q","Niigata");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "山梨_甲府");
        map.put("q","Kofu");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "長野");
        map.put("q","Nagano");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "静岡");
        map.put("q","Shizuoka");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "愛知_名古屋");
        map.put("q","Nagoya");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "岐阜");
        map.put("q","Gifu");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "富山");
        map.put("q","Toyama, JP");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "石川_金沢");
        map.put("q","Kanazawa");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "福井");
        map.put("q","Fukui");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "三重_津");
        map.put("q","Tsu");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "滋賀_大津");
        map.put("q","Otsu");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "奈良");
        map.put("q","Nara");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "和歌山");
        map.put("q","Wakayama");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "京都");
        map.put("q","Kyoto");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "大阪");
        map.put("q","Osaka");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "兵庫_神戸");
        map.put("q","Kobe");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "岡山");
        map.put("q","Okayama");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "鳥取");
        map.put("q","Tottori");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "広島");
        map.put("q","Hiroshima");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "島根_松江");
        map.put("q","Matsue");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "山口");
        map.put("q","Yamaguchi");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "香川_高松");
        map.put("q","Takamatsu");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "徳島");
        map.put("q","Tokushima");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "愛媛_松山");
        map.put("q","Matsuyama");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "高知");
        map.put("q","Kochi,JP");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "福岡");
        map.put("q","Fukuoka");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "佐賀");
        map.put("q","Saga");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "長崎");
        map.put("q","Nagasaki");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "大分");
        map.put("q","Oita");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "熊本");
        map.put("q","Kumamoto");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "宮崎");
        map.put("q","Miyazaki");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "鹿児島");
        map.put("q","Kagoshima");
        list.add(map);

        map = new HashMap<>();
        map.put("name", "沖縄_那覇");
        map.put("q","Naha");
        list.add(map);

        return list;
    }

    @UiThread
    private void receiveWeatherInfo(final String urlFull) {
        Looper mainLooper = Looper.getMainLooper();
        Handler handler = HandlerCompat.createAsync(mainLooper);
        WeatherInfoBackgroundReceiver backgroundReceiver = new WeatherInfoBackgroundReceiver(handler, urlFull);
        ExecutorService executorService  = Executors.newSingleThreadExecutor();
        executorService.submit(backgroundReceiver);
    }

    private class WeatherInfoBackgroundReceiver implements Runnable {

        private final Handler _handler;
        private final String _urlFull;

        public WeatherInfoBackgroundReceiver(Handler handler , String urlFull) {
            _handler = handler;
            _urlFull = urlFull;
        }

        @UiThread
        @Override
        public void run() {
            HttpURLConnection con = null;
            InputStream is = null;
            String result = "";
            try {
                URL url = new URL(_urlFull);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(1000);
                con.setReadTimeout(1000);
                con.setRequestMethod("GET");
                con.connect();
                is = con.getInputStream();
                result = is2String(is);
            }
            catch(MalformedURLException ex) {
                Log.e(DEBUG_TAG, "URL変換失敗", ex);
            }
            catch(SocketTimeoutException ex) {
                Log.w(DEBUG_TAG, "通信タイムアウト", ex);
            }
            catch(IOException ex) {
                Log.e(DEBUG_TAG, "通信失敗", ex);
            }
            finally {
                if(con != null) {
                    con.disconnect();
                }
                if(is != null) {
                    try {
                        is.close();
                    }
                    catch(IOException ex) {
                        Log.e(DEBUG_TAG, "InputStream解放失敗", ex);
                    }
                }
            }
            WeatherInfoPostExecutor postExecutor = new WeatherInfoPostExecutor(result);
            _handler.post(postExecutor);
        }

        private String is2String(InputStream is) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            char[] b = new char[1024];
            int line;
            while(0 <= (line = reader.read(b))) {
                sb.append(b, 0, line);
            }
            return sb.toString();
        }
    }

    private class WeatherInfoPostExecutor implements Runnable {
        private final String _result;
        public WeatherInfoPostExecutor(String result) {
            _result = result;
        }

        @UiThread
        @Override
        public void run() {
            String cityName = "";
            String weather = "";
            String latitude = "";
            String longitude = "";
            try {
                JSONObject rootJSON = new JSONObject(_result);
                cityName = rootJSON.getString("name");
                JSONObject coordJSON = rootJSON.getJSONObject("coord");
                latitude = coordJSON.getString("lat");
                longitude = coordJSON.getString("lon");
                JSONArray weatherJSONArray = rootJSON.getJSONArray("weather");
                JSONObject weatherJSON = weatherJSONArray.getJSONObject(0);
                weather = weatherJSON.getString("description");
            }
            catch(JSONException ex) {
                Log.e(DEBUG_TAG, "JSON解析失敗", ex);
            }
            String telop = cityName +"の天気";
            String desc = "天気は" + weather + "です。\n緯度：" + latitude + "度　経度：" + longitude + "度";
            TextView tvWeatherTelop = findViewById(R.id.Weather_Place_TV);
            TextView tvWeatherDesc = findViewById(R.id.Weather_Detail_TV);
            tvWeatherTelop.setText(telop);
            tvWeatherDesc.setText(desc);
        }
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> item = _list.get(position);
            String q = item.get("q");
            String urlFull = WEATHERINFO_URL + "&q=" + q + "&appid=" + APP_ID;

            receiveWeatherInfo(urlFull);
        }
    }
}