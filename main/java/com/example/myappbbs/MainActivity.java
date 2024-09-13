package com.example.myappbbs;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button bt_updatee;
    private Button bt_deletee;
    private Button bt_read;

    private static int sta = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent data = getIntent();
        String user7 = data.getStringExtra("userName");
        int i = data.getIntExtra("isLogin",0);
        if(i>0)sta=1;
        //Toast.makeText(MainActivity.this,"userName="+user7,Toast.LENGTH_SHORT).show();
        ImageButton IbPersonalCenter = findViewById(R.id.bt_personal);
        //跳转到个人中心界面
        IbPersonalCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sta!=1){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(MainActivity.this, PersonalCenterActivity.class);
                    intent.putExtra("user",user7);
                    intent.putExtra("isLogin",sta);
                    startActivity(intent);
                    sta = 1;
                }
            }
        });

        ImageButton IbAdd = findViewById(R.id.bt_add);
        IbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(sta!=1){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {*/
                    Intent intent = new Intent(MainActivity.this, AddGamesActivity.class);
                    intent.putExtra("user", user7);
                    intent.putExtra("isLogin", sta);
                    startActivity(intent);
                //}
            }
        });

        bt_updatee = findViewById(R.id.bt_updatee);
        bt_updatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sta!=1){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent_query = new Intent(MainActivity.this, UpdateGamesActivity.class);
                    intent_query.putExtra("user", user7);
                    intent_query.putExtra("isLogin", sta);
                    startActivity(intent_query);
                    sta = 1;
                }
            }
        });

        bt_deletee = findViewById(R.id.bt_deletee);
        bt_deletee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent_update = new Intent(MainActivity.this, DeleteGamesActivity.class);
                    startActivity(intent_update);
            }
        });

        bt_read = findViewById(R.id.bt_read);
        bt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_delete = new Intent(MainActivity.this, QueryGamesActivity.class);
                intent_delete.putExtra("user",user7);
                intent_delete.putExtra("isLogin",sta);
                //Toast.makeText(MainActivity.this,"userName="+user7,Toast.LENGTH_SHORT).show();
                startActivity(intent_delete);
                sta = 1;
            }
        });
    }
}