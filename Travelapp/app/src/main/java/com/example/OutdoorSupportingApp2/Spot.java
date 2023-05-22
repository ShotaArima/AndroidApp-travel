package com.example.OutdoorSupportingApp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toolbar;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Spot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);
//戻るボタン
        Button BackButton = findViewById(R.id.bt_s_back);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.layout.activity_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.spot_title);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setSupportActionBar(Toolbar toolbar)
    {

    }
    public void MapSearchBT(View view) {
        EditText etWord = findViewById(R.id.place_edit);
        Spinner genre = (Spinner)findViewById(R.id.genre_list);
        String genreWord = (String)genre.getSelectedItem();
        String searchWord = etWord.getText().toString() + "　" + genreWord;

        try {
            searchWord = URLEncoder.encode(searchWord, "UTF-8");
            String uriStr = "geo:0,0?q=" + searchWord;
            Uri uri =Uri.parse(uriStr);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        catch(UnsupportedEncodingException ex) {
            Log.e("Spot","検索ワード変換失敗",ex);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}