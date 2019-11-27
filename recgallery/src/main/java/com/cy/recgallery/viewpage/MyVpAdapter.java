package com.cy.recgallery.viewpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cy.recgallery.R;
import com.cy.recgallery.view.TwinkleImagView;

import java.util.List;

/**
 * Created by lenovo on 2019/6/14
 * 功能描述：
 */
public class MyVpAdapter extends PagerAdapter {

    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private TwinkleImagView imageView;
    private View mCurrentView;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MyVpAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mCurrentView= (View) object;
        super.setPrimaryItem(container, position, object);
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.item_rec, container, false);
        imageView = view.findViewById(R.id.image);
        Glide.with(mContext).load(mDatas.get(position)).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*TwinkleImagView view = mCurrentView.findViewById(R.id.image);
                view.stopAnimation();
                view.startAnimation();*/
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onClick(v);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface OnItemClickListener{
        void onClick(View view);
    }
}
