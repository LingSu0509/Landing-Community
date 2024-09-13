package com.example.myappbbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myappbbs.bean.Game;
import com.example.myappbbs.utils.MySQLiteOpenHelper;


public class UpdateGamesActivity extends AppCompatActivity {
    //组件定义
    private EditText etGameid;
    private EditText etGamename;
    //private EditText etGametime;
    private EditText etGamenote;
    private Button btnSearch;
    private Button btnEdit,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_games);
        initView();
    }
    private void initView() {
        etGameid = findViewById(R.id.et_gameid);
        btnSearch = findViewById(R.id.btn_search);
        etGamename = findViewById(R.id.et_gamename);
        //etGametime = findViewById(R.id.et_gametime);
        etGamenote = findViewById(R.id.et_gamenote);
        btnEdit =  findViewById(R.id.btn_edit);
        btnback = findViewById(R.id.btn_ba4);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateGamesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchOrder();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrder();
            }
        });
    }
    private void searchOrder() {
            String gameid = etGameid.getText().toString().trim();
            MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
            dao.open();
            Game game = dao.getGames(gameid);
            etGameid.setText(game.gameid);
            etGamename.setText(game.gamename);
            etGamenote.setText(game.gamenote);
            dao.close();
    }

    private void updateOrder() {
            String id6 = etGameid.getText().toString();
            Game game=new Game();
            game.gameid=etGameid.getText().toString().trim();
            game.gamename = etGamename.getText().toString().trim();
            //game.gametime=etGametime.getText().toString().trim();
            game.gamenote = etGamenote.getText().toString().trim();


            MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
            dao.open();
            long result = dao.updateGames(game);
            if (result > 0) {
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateGamesActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
            }
            dao.close();
    }


}