package com.example.myappbbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myappbbs.bean.Game;
import com.example.myappbbs.utils.MySQLiteOpenHelper;

public class DeleteGamesActivity extends AppCompatActivity{

    private EditText etGameid1;
    private Button btnSearch1;
    private EditText etGamename1;
    private EditText etGametime1;
    private EditText etGamenote1;
    private Button btnDelete1;
    private Button btnBacke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_games);
        initView();
    }
    private void initView() {
        etGameid1=(EditText) findViewById(R.id.et_gameid);
        btnSearch1=(Button) findViewById(R.id.btn_search);
        etGamename1=(EditText)findViewById(R.id.et_gamename);
        etGametime1=(EditText)findViewById(R.id.et_gametime);
        etGamenote1=(EditText)findViewById(R.id.et_content6);
        btnDelete1= (Button) findViewById(R.id.btn_delete);
        btnBacke= (Button) findViewById(R.id.btn_ba3);
        btnBacke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteGamesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchOrder1();
            }
        });
        btnDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
                etGameid1.setText("");
                etGamename1.setText("");
                etGamenote1.setText("");
                etGametime1.setText("");
                Handler handler = new Handler();
                //当计时结束,跳转至主界面
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DeleteGamesActivity.this, MainActivity.class);
                        startActivity(intent);
                        DeleteGamesActivity.this.finish();
                    }
                }, 1000);
            }
        });
    }

    //查找信息
    private void searchOrder1() {
        String studentid=etGameid1.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        Game game=dao.getGames(studentid);
        etGamename1.setText(game.gamename);
        etGametime1.setText(game.gametime);
        etGamenote1.setText(game.gamenote);
        dao.close();
    }
    private void deleteOrder() {
        Game game=new Game();
        game.gameid=etGameid1.getText().toString().trim();
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        int result= dao.deletGames(game);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }

}