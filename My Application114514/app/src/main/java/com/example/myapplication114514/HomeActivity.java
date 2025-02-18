package com.example.myapplication114514;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    public static void startActivity1(Context context,String username){
        Intent intent=new Intent(context,HomeActivity.class);
        intent.putExtra("username",username);
        context.startActivity(intent);
    }/*封面跳转*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TabLayout tabLayout=findViewById(R.id.tabLout);
        ViewPager2 vp2=findViewById(R.id.tab_viewPager);
        ArrayList<VPAdapter.FragmentInterface> fragmentList=new ArrayList<>();
        fragmentList.add(new VPAdapter.FragmentInterface() {
            @Override
            public Fragment back() {
                return new FirstFragment();
            }
        });
        fragmentList.add(new VPAdapter.FragmentInterface() {
            @Override
            public Fragment back() {
                return new SecondFragment();
            }
        });
        VPAdapter adapter=new VPAdapter(this,fragmentList);
        vp2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("首页");
                }else{
                    tab.setText("我的");
                }
            }
        }).attach();

    }/*分页设置*/
}