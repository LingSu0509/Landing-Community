package com.example.myappbbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myappbbs.utils.MySQLiteOpenHelper;

import java.util.List;
import java.util.Map;

public class QueryGamesActivity extends AppCompatActivity {
    private ListView listView;
    public Context createDeviceProtectedStorageContext() {
        return super.createDeviceProtectedStorageContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_games);
        setTitle("查看记录");

        Intent intent = getIntent();
        String user1= intent.getStringExtra("user");
        int sta= intent.getIntExtra("isLogin",0);
        //Toast.makeText(QueryGamesActivity.this,"userName1="+user1,Toast.LENGTH_SHORT).show();

        listView=(ListView)findViewById(R.id.lst_orders);
        initView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Map<String, Object> rowData = (Map<String, Object>) adapterView.getItemAtPosition(position);
                String gameId = (String) rowData.get("gamid");
                String gamename = (String) rowData.get("gamename");
                String gametime = (String) rowData.get("gametime");
                String gamenote = (String) rowData.get("gamenote");
                Intent intent = new Intent(QueryGamesActivity.this,ReadGameActivity.class);
                intent.putExtra("gameid",gameId);
                intent.putExtra("gamename",gamename);
                intent.putExtra("gametime",gametime);
                intent.putExtra("gamenote",gamenote);
                intent.putExtra("user",user1);
                startActivity(intent);
            }
        });

        Button btn_ba=(Button)findViewById(R.id.bt_ba);
        btn_ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryGamesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initView() {
        MySQLiteOpenHelper dao=new MySQLiteOpenHelper(getApplicationContext());
        dao.open();
        List<Map<String,Object>> mOrderData=dao.getAllGames();
        String[] from={"gameid","gamename","gametime","gamenote"};
        int[] to={R.id.tv_lst_gameid,R.id.tv_lst_gamename,R.id.tv_lst_gametime,R.id.tv_lst_gamenote};
        SimpleAdapter listItemAdapter=new SimpleAdapter(QueryGamesActivity.this,mOrderData,R.layout.item_list,from,to);
        listView.setAdapter(listItemAdapter);
        Intent intent = getIntent();
        int sta= intent.getIntExtra("isLogin",0);
        //Intent intent1 = new Intent(QueryGamesActivity.this,ReadGameActivity.class);
        //intent1.putExtra("isLogin",sta);
        //startActivity(intent1);
        dao.close();
    }

}