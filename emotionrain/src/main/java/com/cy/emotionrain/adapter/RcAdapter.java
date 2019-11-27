package com.cy.emotionrain.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cy.emotionrain.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int i) {
        holder.setData(i);
        //单击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //触发自定义监听的单击事件
                onItemClickListener.onItemClick(holder.itemView,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image)
        ImageView mImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(int position) {
            Glide.with(mImageView.getContext()).load(datas.get(position))
                    //高斯模糊
                    //.apply(bitmapTransform(new BlurTransformation(25, 3)))
                    .into(mImageView);
        }
    }

    public void setOnItemClickListener(RcAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;
    /**
     * 自定义监听回调，RecyclerView 的 单击和长按事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
