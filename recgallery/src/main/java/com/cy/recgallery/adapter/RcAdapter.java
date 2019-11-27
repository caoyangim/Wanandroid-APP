package com.cy.recgallery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cy.recgallery.R;

import java.util.List;

/**
 * Created by lenovo on 2019/5/31
 * 功能描述：
 */
public class RcAdapter extends RecyclerView.Adapter<RcAdapter.ViewHolder> {
    private List<String> datas;
    private Context mContext;

    public RcAdapter(List<String> datas, Context context) {
        this.datas = datas;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rec,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }

        public void setData(int position) {
            Glide.with(mImageView.getContext()).load(datas.get(position)).into(mImageView);
        }
    }
}
