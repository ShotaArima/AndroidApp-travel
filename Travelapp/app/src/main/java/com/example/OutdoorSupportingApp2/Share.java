package com.example.OutdoorSupportingApp2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Share extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        final String[] Address = {""};
        final int[] person = {0};


        //戻るボタン
        Button BackButton = findViewById(R.id.Mail_Back_Button);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button AddressBT = (Button) this.findViewById(R.id.Share_Address_BT);
        AddressBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id =findViewById(R.id.Share_MyId_ET);
                String Id = id.getText().toString();
                Address[0] += Id + "@gmail.com,";
                id.setText("");
                person[0] += 1;
                TextView Person = findViewById(R.id.Share_PesonNum_TV);
                Person.setText(String.valueOf(person[0]));
            }
        });

        Button btnSend = (Button) this.findViewById(R.id.Share_Send_BT);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title =findViewById(R.id.Share_Title_ET);
                EditText text =findViewById(R.id.Share_Text_ET);
                String[] MailAddress = Address;
                String MailTitle = title.getText().toString();
                String MailText = text.getText().toString();
                composeEmail(MailAddress, MailTitle, MailText);
            }
        });
    }

    public void composeEmail(String[] addresses, String subject, String maintext) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, maintext);
        startActivity(intent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }






}