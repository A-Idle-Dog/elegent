package com.example.myapplication114514;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.NovelViewHolder> {
    private final List<Novel> dataList;
    FirstFragment context;
    public NovelAdapter(List<Novel> dataList, FirstFragment context) {
        this.dataList = dataList;
        this.context=context;
    }

    public NovelAdapter.NovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new NovelViewHolder(view);

    }

    public void onBindViewHolder(@NonNull NovelViewHolder holder, int position) {
        Novel novel = dataList.get(position);
        holder.mIvCover.setImageResource(novel.getCover());
        holder.mTvTitle.setText(novel.getTitle());
        holder.mTvSummary.setText(novel.getSummary());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "onClick: 准备跳转");
                ReadActivity.toread(context,novel.getFilename(),novel.getTitle());
            }/*创建跳转*/
        });
    }

    public int getItemCount() {
        return dataList.size();
    }



    public  class NovelViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvCover;
        TextView mTvTitle;
        TextView mTvSummary;

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvCover = itemView.findViewById(R.id.iv_item_cover);
            mTvTitle = itemView.findViewById(R.id.tv_item_title);
            mTvSummary = itemView.findViewById(R.id.tv_item_summary);
        }



    }
}



