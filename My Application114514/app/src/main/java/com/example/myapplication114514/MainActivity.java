package com.example.myapplication114514;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.PrivateKey;

import javax.security.auth.login.LoginException;


public class MainActivity extends AppCompatActivity {
    private EditText mEtUsername;
    private EditText mEtPassWord;
    private TextView mTvRegister;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    private void initView(){
        mEtUsername=findViewById(R.id.et_main_username);
        mEtPassWord=findViewById(R.id.et_main_password);
        mBtnLogin=findViewById(R.id.btn_main_login);
        mTvRegister=findViewById(R.id.tv_main_regisetr);
    }
    private void initEvent(){
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toregister();
            }
        });

    }//设置点击事件,调用登陆页面到主页面以及注册页面的跳转。
    private void login(){
        String username=mEtUsername.getText().toString();
        String password=mEtPassWord.getText().toString();
        SharedPreferences sharedPreferences=getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String savedUsername=sharedPreferences.getString("username","");
        String savedPassword=sharedPreferences.getString("password","");
        if(username.equals(savedUsername) && password.equals(savedPassword)&&!username.isEmpty()&&!password.isEmpty()){
            loginSuccess();
        }else {
            loginFail();
        }
    }//验证密码并登录。
    private void loginSuccess(){
        Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
        HomeActivity.startActivity1(this,mEtUsername.getText().toString());
    }
    private void loginFail(){
        Toast.makeText(this,"登陆失败",Toast.LENGTH_SHORT).show();
    }//分别设置登录成功与失败的提示。
    private void  toregister(){
        RegisterActivity.startActivity2(this);
    }
}