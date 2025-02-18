package com.example.myapplication114514;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentStateAdapter {
    private final ArrayList<FragmentInterface> fragments;
    public  VPAdapter(@NonNull FragmentActivity fragmentActivity,ArrayList<FragmentInterface> fragments){
        super(fragmentActivity);
        this.fragments=fragments;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position){
        return fragments.get(position).back();
    }
    public interface FragmentInterface{
        public Fragment back();
    }
    public int getItemCount(){
        return fragments.size();
    }

}
