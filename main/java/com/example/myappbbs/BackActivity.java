package com.example.myappbbs;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BackActivity extends AppCompatActivity {

    private Button bt_backfalse;
    private Button bt_backtrue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        bt_backfalse = findViewById(R.id.bt_backfalse);
        bt_backfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_insert = new Intent();
                intent_insert.setClass(BackActivity.this, PersonalCenterActivity.class);
                startActivity(intent_insert);
            }
        });

        bt_backtrue = findViewById(R.id.bt_backtrue);
        bt_backtrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_query = new Intent();
                intent_query.setClass(BackActivity.this, LoginActivity.class);
                startActivity(intent_query);
            }
        });

    }

}