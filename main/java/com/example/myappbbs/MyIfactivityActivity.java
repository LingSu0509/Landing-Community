package com.example.myappbbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappbbs.bean.Student;
import com.example.myappbbs.utils.StudentDbHelper;

import java.util.LinkedList;


public class MyIfactivityActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    TextView tvStuName,tvStuMajor,tvStuPhone,tvStuQq,tvStuAddress,TextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ifactivity);
        Button btnBack = findViewById(R.id.btn_back);
        //返回点击事件,销毁当前界面
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyIfactivityActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //从bundle中获取用户账号/学号
        final TextView tvUserNumber = findViewById(R.id.tv_stu_number);
        tvUserNumber.setText(this.getIntent().getStringExtra("stu_number1"));
        tvStuName = findViewById(R.id.tv_stu_name);
        tvStuMajor = findViewById(R.id.tv_stu_major);
        tvStuPhone = findViewById(R.id.tv_stu_phone);
        tvStuQq = findViewById(R.id.tv_stu_qq);
        tvStuAddress = findViewById(R.id.tv_stu_address);
        StudentDbHelper dbHelper = new StudentDbHelper(getApplicationContext(),StudentDbHelper.DB_NAME,null,1);
        LinkedList<Student> students = dbHelper.readStudents(tvUserNumber.getText().toString());
        if(students != null) {
            for(Student student : students) {
                tvStuName.setText(student.getStuName());
                tvStuMajor.setText(student.getStuMajor());
                tvStuPhone.setText(student.getStuPhone());
                tvStuQq.setText(student.getStuQq());
                tvStuAddress.setText(student.getStuAddress());
            }
        }else {
            tvStuName.setText("暂未填写");
            tvStuMajor.setText("暂未填写");
            tvStuPhone.setText("暂未填写");
            tvStuQq.setText("暂未填写");
            tvStuAddress.setText("暂未填写");
        }
        Button btnModifyInfo = findViewById(R.id.btn_modify_info);
        //跳转到修改用户信息界面
        btnModifyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyIfactivityActivity.this,UpdateIfactivityActivity.class);
                intent.putExtra("stu_number2",tvUserNumber.getText().toString());
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CODE ){
            if(resultCode == RESULT_OK){
                TextView id8 = findViewById(R.id.tv_stu_number);
                String id = data.getStringExtra("gameid");
                String name = data.getStringExtra("Name");
                String Major = data.getStringExtra("Major");
                String Phone = data.getStringExtra("Phone");
                String QQ = data.getStringExtra("QQ");
                String Address = data.getStringExtra("Address");
                id8.setText(id);
                tvStuName.setText(name);
                tvStuMajor.setText(Major);
                tvStuPhone.setText(Phone);
                tvStuQq.setText(QQ);
                tvStuAddress.setText(Address);
            }
        }
    }
}