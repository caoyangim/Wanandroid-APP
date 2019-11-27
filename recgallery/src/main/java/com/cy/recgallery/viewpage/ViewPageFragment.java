package com.cy.recgallery.viewpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cy.recgallery.R;
import com.cy.recgallery.view.TwinkleImagView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by lenovo on 2019/6/14
 * 功能描述：
 */
public class ViewPageFragment extends Fragment {

    @BindView(R.id.viewPage)
    ViewPager mViewPager;
    @BindView(R.id.bac_img)
    ImageView bac_img;

    private MyVpAdapter adapter;

    private Handler mHandle = new Handler();

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            View childAt = adapter.getPrimaryItem();
            TwinkleImagView img = childAt.findViewById(R.id.image);
            img.stopAnimation();
            img.startAnimation();
    }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,null,false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        final List<String> datas = Arrays.asList(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559884528&di=4b78eb906804b4d7c4c75f780b8555e9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201609%2F15%2F20160915103550_FxWCE.jpeg"
                ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559884528&di=4b78eb906804b4d7c4c75f780b8555e9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201609%2F15%2F20160915103550_FxWCE.jpeg"
                ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289723579&di=ce622578ba9bf431ee55c43c3b4b8c27&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fcedeae5c5f717e1acfe31473d54190998c5508771881d-TNHJ2b_fw658"
                ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289766095&di=637e259bc304c4f575bf10ba4f9aee6b&imgtype=0&src=http%3A%2F%2Fimage.namedq.com%2Fuploads%2F20181219%2F23%2F1545233794-OpGuernCUI.jpg"
                ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289876960&di=c95a33a8cb9cadab370dd3921a601b3a&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F21%2F20170421192631_ZXJiV.thumb.700_0.jpeg"
                ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559289810171&di=5001fedaf150ba7a09982747d70e834f&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F01%2F20160201013529_V8zT3.thumb.700_0.jpeg"
                ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559884528&di=4b78eb906804b4d7c4c75f780b8555e9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201609%2F15%2F20160915103550_FxWCE.jpeg");

        adapter = new MyVpAdapter(getContext(),datas);
        adapter.setOnItemClickListener(new MyVpAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view) {
                View childAt = adapter.getPrimaryItem();
                TwinkleImagView img = childAt.findViewById(R.id.image);
                img.stopAnimation();
                img.startAnimation();
            }
        });
        mViewPager.setAdapter(adapter);
        //预加载左边和右边3个
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(false,new MyVpTransform());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Glide.with(getActivity()).load(datas.get(i))
                        .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,3)))
                        .into(bac_img);
                //mHandle.postDelayed(r,600);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
