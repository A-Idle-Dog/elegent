package com.example.myapplication114514;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadActivity extends AppCompatActivity implements Runnable {
    private TextView mTvNovel;
    private TextView mTvTitle;
    private ImageView mIvBack;
    private ImageView mIvMoon;
    private Boolean isnight;

    public static void toread(FirstFragment fragment, String filename, String title) {
        if (fragment != null) {
            Intent intent = new Intent(fragment.getActivity(), ReadActivity.class);
            intent.putExtra("filename", filename);
            intent.putExtra("title", title);
            fragment.startActivity(intent);
        } else {
            Log.e("toread", "toread: 没有附加");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadColorMode();
        Log.d("NightMode", "isNight: " + isnight);
        setContentView(R.layout.activity_read);
        initView();
        initEvent();
        run();
    }

    private void initView() {
        mTvNovel = findViewById(R.id.tv_read_novel);
        mTvTitle = findViewById(R.id.tv_read_title);
        mIvBack=findViewById(R.id.iv_read_back);
        mIvMoon=findViewById(R.id.iv_read_moon);
    }

    public String readText(Context context, String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "文件读取失败" + e.getMessage();
        }
        return sb.toString();
    }/*读取文件*/

    public void run() {
        String content = readText(this, getIntent().getStringExtra("filename"));
        mTvNovel.setText(content.toString());
        mTvTitle.setText(getIntent().getStringExtra("title"));
    }

    private void initEvent() {
        if (isnight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReadActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });
        mIvMoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isnight=!isnight;
                Log.d("NightMode2", "isNight: " + isnight);
                saveColorMode(isnight);
                recreate();
            }
        });
    }
    private void loadColorMode(){
        SharedPreferences mode=getSharedPreferences("color",MODE_PRIVATE);
        isnight=mode.getBoolean("key",false);
    }
    private void saveColorMode(boolean isNight){
        SharedPreferences mode=getSharedPreferences("color",MODE_PRIVATE);
        SharedPreferences.Editor editor=mode.edit();
        editor.putBoolean("key",isNight);
        editor.apply();
    }
}