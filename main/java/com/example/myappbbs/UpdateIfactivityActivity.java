package com.example.myappbbs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappbbs.bean.Student;
import com.example.myappbbs.utils.StudentDbHelper;

import java.util.LinkedList;

public class UpdateIfactivityActivity extends AppCompatActivity {
    EditText etStuName,etMajor,etPhone,etQq,etAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ifactivity);
        Button btnBack = findViewById(R.id.btn_back);
        //返回按钮点击事件
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //利用bundle传递学号
        final TextView tvStuNumber = findViewById(R.id.tv_stu_number);
        tvStuNumber.setText(this.getIntent().getStringExtra("stu_number2"));
        etStuName = findViewById(R.id.et_stu_name);
        etMajor = findViewById(R.id.et_stu_major);
        etPhone = findViewById(R.id.et_stu_phone);
        etQq = findViewById(R.id.et_stu_qq);
        etAddress = findViewById(R.id.et_stu_address);
        StudentDbHelper dbHelper = new StudentDbHelper(getApplicationContext(),StudentDbHelper.DB_NAME,null,1);
        LinkedList<Student> students = dbHelper.readStudents(tvStuNumber.getText().toString());
        //如果查找到的学生信息不为空
        if(students != null) {
            for(Student student : students) {
                etStuName.setText(student.getStuName());
                etMajor.setText(student.getStuMajor());
                etPhone.setText(student.getStuPhone());
                etQq.setText(student.getStuQq());
                etAddress.setText(student.getStuAddress());
            }
        }
        Button btnSaveInfo = findViewById(R.id.btn_save_info);
        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断输入不为空
                if(CheckInput()) {
                    StudentDbHelper dbHelper = new StudentDbHelper(getApplicationContext(),StudentDbHelper.DB_NAME,null,1);
                    Student student = new Student();

                    String id = tvStuNumber.getText().toString().trim();
                    String Name = etStuName.getText().toString().trim();
                    String Address = etAddress.getText().toString().trim();
                    String Major = etMajor.getText().toString().trim();
                    String Phone = etPhone.getText().toString().trim();
                    String QQ = etQq.getText().toString().trim();


                    student.setStuNumber(id);
                    student.setStuName(Name);
                    student.setStuMajor(Major);
                    student.setStuPhone(Phone);
                    student.setStuQq(QQ);
                    student.setStuAddress(Address);
                    dbHelper.saveStudent(student);
                    Toast.makeText(getApplicationContext(),"用户信息保存成功!",Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    //当计时结束,跳转至主界面
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.putExtra("gameid",id);
                            intent.putExtra("Name",Name);
                            intent.putExtra("Major",Major);
                            intent.putExtra("Phone",Phone);
                            intent.putExtra("QQ",QQ);
                            intent.putExtra("Address",Address);
                            setResult(RESULT_OK,intent);
                            UpdateIfactivityActivity.this.finish();
                        }
                    }, 1000);
                }
            }
        });
    }

    //检查输入是否为空
    public boolean CheckInput() {
        String StuName = etStuName.getText().toString();
        String StuMajor = etMajor.getText().toString();
        String StuPhone = etPhone.getText().toString();
        String StuQq = etQq.getText().toString();
        String StuAddress = etAddress.getText().toString();
        if(StuName.trim().equals("")) {
            Toast.makeText(this,"姓名不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuMajor.trim().equals("")) {
            Toast.makeText(this,"专业不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuPhone.trim().equals("")) {
            Toast.makeText(this,"联系方式不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuQq.trim().equals("")) {
            Toast.makeText(this,"QQ号不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StuAddress.trim().equals("")) {
            Toast.makeText(this,"地址不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}