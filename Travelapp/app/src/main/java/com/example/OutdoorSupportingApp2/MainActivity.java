package com.example.OutdoorSupportingApp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Uri _imageUri;
    private double _latitude = 0;
    private double _longitude = 0;
    private FusedLocationProviderClient _fusedLocationClient;
    private LocationRequest _locationRequest;
    private Handler mHandler;
    private Timer mTimer;
    private static SimpleDateFormat mSimpleDataFormat = new SimpleDateFormat("  MM/dd　HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] FileName = new String[1];

        _fusedLocationClient= LocationServices.getFusedLocationProviderClient(MainActivity.this);
        _locationRequest = LocationRequest.create();
        _locationRequest.setInterval(5000);
        _locationRequest.setFastestInterval(1000);
        _locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mHandler = new Handler(getMainLooper());
        mTimer = new Timer();



//        時計機能
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        String nowDate = mSimpleDataFormat.format(calendar.getTime());
                        // 時刻表示をするTextView
                        ((TextView) findViewById(R.id.clock)).setText(nowDate);
                    }
                });}
        },0,1000);

        Button SpotButton = findViewById(R.id.spot_button);
        SpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),Spot.class);
                startActivity(intent);
            }
        });


        //カメラ画面
        Button CameraButton = findViewById(R.id.Home_Camera_BT);
        CameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date now = new Date(System.currentTimeMillis());
                String nowStr = dateFormat.format(now);
                String fileName = "CameraIntentSamplePhoto_" + nowStr +".jpg";
                FileName[0] = fileName;
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                ContentResolver resolver = getContentResolver();
                _imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, _imageUri);
                startActivityForResult(intent, 200);
            }
        });

        //天気画面
        Button WeatherButton = findViewById(R.id.Home_Shift_Weather_BT);
        WeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),Weather.class);
                startActivity(intent);
            }
        });

//        言語切り替え
        Button LangButton = findViewById(R.id.home_lang_BT);
        LangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }
        });


        //共有画面
        Button MailButton = findViewById(R.id.Home_Shift_Mail_BT);
        MailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),Mail.class);
                startActivity(intent);
            }
        });

        //出発地登録
        Button DepClick = findViewById(R.id.Home_Way_BT);
        DepClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                EditText etDep = findViewById(R.id.H_D_ET);
                String note = etDep.getText().toString();

               TextView DepTv = findViewById(R.id.Home_DepPlace_TV);
               DepTv.setText(note);

                try {
                    note = URLEncoder.encode(note, "UTF-8");
                    String uriStr = "geo:0.0?q=" + note;
                    Uri uri = Uri.parse(uriStr);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                catch (UnsupportedEncodingException ex)
                {
                    Log.e("MainActivity", "検索キーワード変換失敗");
                }
            }
        });

        //目的地登録
        Button PurClick = findViewById(R.id.Home_Pur_BT);
        PurClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                EditText etPur = findViewById(R.id.Home_Pur_ET);
                String note = etPur.getText().toString();

                TextView PurTv = findViewById(R.id.Home_PurPlace_TV);
                PurTv.setText(note);

                try {
                    note = URLEncoder.encode(note, "UTF-8");
                    String uriStr = "geo:0.0?q=" + note;
                    Uri uri = Uri.parse(uriStr);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
                catch (UnsupportedEncodingException ex)
                {
                    Log.e("MainActivity", "検索キーワード変換失敗");
                }
            }
        });
    }


    private class spotlistener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 定期実行をcancelする
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

}

