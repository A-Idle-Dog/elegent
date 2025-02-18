package com.example.myapplication114514;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEtUsername;
    private EditText mEtPassWord;
    private Button mBtnRegister;
    public static void startActivity2(Context context){
        Intent intent=new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }
    private void initView(){
        mEtUsername=findViewById(R.id.et_register_username);
        mEtPassWord=findViewById(R.id.et_register_password);
        mBtnRegister=findViewById(R.id.btn_register_login);
    }
    private void initEvent(){
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void register(){
        String username=mEtUsername.getText().toString();
        String password=mEtPassWord.getText().toString();
        if(username.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"用户名与密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedpreferences=getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.apply();
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }

}