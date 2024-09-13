package com.example.myappbbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myappbbs.bean.Game;
import com.example.myappbbs.utils.MySQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddGamesActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etGameid;
    private EditText etGamename;
    private EditText etGamenote;

    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_games);
        //初始化界面
        initView();
    }
    //初始化界面
    private void initView() {
        etGameid=(EditText)findViewById(R.id.et_gameid);
        etGamename = (EditText) findViewById(R.id.et_gamename);
        etGamenote = (EditText) findViewById(R.id.et_gamenote);

        btnAdd = (Button) findViewById(R.id.btn_add);
        //设置按钮的点击事件
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //当单击“添加”按钮时，获取添加信息
        String gameid=etGameid.getText().toString().trim();
        String gamename = etGamename.getText().toString().trim();
        String gamenote = etGamenote.getText().toString();
            //检验信息是否正确
        if (TextUtils.isEmpty(gameid)) {
            Toast.makeText(this, "请输入id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gamename)) {
            Toast.makeText(this, "请输入科目", Toast.LENGTH_SHORT).show();
            return;
            }
        if (TextUtils.isEmpty(gamenote)) {
            Toast.makeText(this, "请输入计划", Toast.LENGTH_SHORT).show();
            return;
        }

            long time = System.currentTimeMillis();
            Date date = new Date(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String CT = sdf.format(date);
            //添加信息
            Game game = new Game();
            game.gameid = gameid;
            game.gamename = gamename;
            game.gametime = CT;
            game.gamenote = gamenote;


            //创建数据库访问对象
            MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
            dao.open();
            long result = dao.addGames(game);

            if (result > 0) {
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(AddGamesActivity.this, MainActivity.class);
                startActivity(intent3);
            } else {
                Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
            }
            dao.close();
            //finish();

    }


}