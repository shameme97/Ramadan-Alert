package com.example.ramadanalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btn2;
    Button btn5;
    Button btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeActivity();

            }
        });


     btn5 = findViewById(R.id.button5);
     btn5.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             openPdfActivity();
         }
     });

     btn6 = findViewById(R.id.button6);
     btn6.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             openAlarmActivity();
         }
     });
    }


    public void openTimeActivity(){
        Intent intent = new Intent(this,TimeActivity.class);
        startActivity(intent);
    }

    public void openPdfActivity(){
        Intent intent = new Intent(this,PdfActivity.class);
        startActivity(intent);
    }

    public void openAlarmActivity(){
        Intent intent = new Intent(this, setAlarm.class);
        startActivity(intent);
    }
}
