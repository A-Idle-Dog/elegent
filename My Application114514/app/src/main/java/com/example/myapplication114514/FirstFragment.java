package com.example.myapplication114514;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NovelAdapter mNovelAdpter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override//准备小说数据列表，创建适配器并将其与 RecyclerView 绑定，最终返回创建好的视图。
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mRecyclerView=view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNovelAdpter=new NovelAdapter(mNovelList(),this);
        mRecyclerView.setAdapter(mNovelAdpter);
        return view;

    }
    private List<Novel> mNovelList(){
        List<Novel> data=new ArrayList<>();
        data.add(new Novel(R.drawable.cover,"太虚剑意","昆仑玄境山外山，乾坤阴阳有洞天。只问真君何处有，不向江湖寻剑仙。华山纯阳宫门下，太虚剑宗弟子瑶光意外离开大唐流落异世。改天换地如何？白云苍狗如何？太虚剑意，一剑凌云。向使长剑在手，九州山河，黄泉碧落，谁人阻我？","novel_1.txt"));
        data.add(new Novel(R.drawable.cover,"媚眼天成","一场车祸，一只手镯，成就了她一身异能，媚眼天成！一件往事，一段噩梦，让她相信只有自己变强才不会任人凌辱！她出身农家，却身怀绝技，断生死，识顽石。从一个任人欺负的普通学生，摇身一变成为所有人眼中的宠儿，苏翊表示压力很大……..","novel_2.txt"));
        return data;
    }
}