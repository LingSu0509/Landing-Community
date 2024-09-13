package com.example.myappbbs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myappbbs.bean.Game;
import com.example.myappbbs.utils.MySQLiteOpenHelper;

public class ReadGameActivity extends AppCompatActivity {

    private ImageView dele;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_game);
        TextView Gameid = (TextView) findViewById(R.id.id1);
        TextView Gamename = (TextView) findViewById(R.id.lesson1);
        TextView Gamenote = (TextView) findViewById(R.id.content1);
        TextView Gametime = (TextView) findViewById(R.id.time1);
        ImageView bt_ba2 = (ImageView) findViewById(R.id.bt_ba1);

        Intent intent2 = getIntent();
        String user2= intent2.getStringExtra("user");
        int sta= intent2.getIntExtra("isLogin",0);

        Intent intent = getIntent();
        String gameid1 = intent.getStringExtra("gameid");
        String gamename1 = intent.getStringExtra("gamename");
        String gamenote1 = intent.getStringExtra("gamenote");
        String gametime1 = intent.getStringExtra("gametime");

        Gameid.setText(gameid1);
        Gametime.setText(gametime1);
        Gamename.setText(gamename1);
        Gamenote.setText(gamenote1);

        bt_ba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        dele = (ImageView) findViewById(R.id.dele);
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText( ReadGameActivity.this, "user2="+user2+"   gameid"+gameid1, Toast.LENGTH_SHORT).show();
                Game game = new Game();
                game.gameid = Gameid.getText().toString().trim();
                MySQLiteOpenHelper dao = new MySQLiteOpenHelper(getApplicationContext());
                dao.open();
                int result = dao.deletGames(game);
                if (result > 0) {
                    Toast.makeText(ReadGameActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReadGameActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ReadGameActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                dao.close();
            }

        });
        //Button btn_thumb = (Button) findViewById(R.id.thumb);
        /*btn_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/



    }
}